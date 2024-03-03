package com.example.whbdemo.controller;

import com.example.whbdemo.dao.ProductRatingsDao;
import com.example.whbdemo.domain.Orders;
import com.example.whbdemo.domain.ProductRatings;
import com.example.whbdemo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@Transactional
@RequestMapping("/products")
public class ProductRatingsController {

    @Autowired
    private ProductRatingsDao productRatingsDao;


    @PostMapping("/insterRroductran")
    public Result insterRroductran(@RequestBody ProductRatings productRatings){

        productRatingsDao.insertproductrating(productRatings);
        return new Result(Code.SAVE_OK , "评分成功");
    }

    @PostMapping("/userrecommend")
    public Result recommendProductsForUser(@RequestBody User user) {
        if(user.getUserId()!=null){
        List<ProductRatings> allRatings = productRatingsDao.rs();

        Map<Integer, Map<Integer, Double>> userRatings = buildUserRatings(allRatings);
        int targetUserId = Integer.parseInt(user.getUserId());

        Optional<Integer> similarUserIdOpt = findMostSimilarUser(userRatings, targetUserId);
        if (!similarUserIdOpt.isPresent()) {
            List<Orders> errorrecommendProducts = productRatingsDao.errorrecommendProducts();

            return new Result (Code.GET_ERR,errorrecommendProducts);
        }
        Integer likeuser = similarUserIdOpt.get();
        List<Orders> recommendProductsForUser = productRatingsDao.recommendProductsForUser(likeuser);

        return new Result(Code.GET_OK,recommendProductsForUser);
        }else {
            List<Orders> errorrecommendProducts = productRatingsDao.errorrecommendProducts();
            return new Result (Code.GET_ERR,errorrecommendProducts);
        }

    }

    private Map<Integer, Map<Integer, Double>> buildUserRatings(List<ProductRatings> ratings) {
        Map<Integer, Map<Integer, Double>> userRatings = new HashMap<>();
        for (ProductRatings rating : ratings) {
            Integer userId = rating.getUserId();
            Integer productId = rating.getProductId();
            Double score = Double.valueOf(rating.getRating());

            userRatings.putIfAbsent(userId, new HashMap<>());

            userRatings.get(userId).put(productId, score);
        }
        return userRatings;
    }
    public Optional<Integer> findMostSimilarUser(Map<Integer, Map<Integer, Double>> userRatings, int targetUserId) {
        if (userRatings == null || userRatings.isEmpty()) {
            return Optional.empty();
        }

        Map<Integer, Double> targetUserRatings = userRatings.get(targetUserId);
        if (targetUserRatings == null || targetUserRatings.isEmpty()) {
            return Optional.empty();
        }

        double maxSimilarity = -1.0;
        Integer mostSimilarUserId = null;

        for (Map.Entry<Integer, Map<Integer, Double>> entry : userRatings.entrySet()) {
            Integer userId = entry.getKey();
            if (userId.equals(targetUserId)) {
                continue;
            }

            Map<Integer, Double> otherUserRatings = entry.getValue();
            if (otherUserRatings == null || otherUserRatings.isEmpty()) {
                continue;
            }

            double similarity = cosineSimilarity(targetUserRatings, otherUserRatings);

            if (similarity > maxSimilarity) {
                maxSimilarity = similarity;
                mostSimilarUserId = userId;
            }
        }

        return Optional.ofNullable(mostSimilarUserId);
    }

    private double cosineSimilarity(Map<Integer, Double> user1Ratings, Map<Integer, Double> user2Ratings) {
        Set<Integer> commonItems = new HashSet<>(user1Ratings.keySet());
        commonItems.retainAll(user2Ratings.keySet());

        if (commonItems.isEmpty()) {
            return 0.0;
        }

        double dotProduct = 0.0;
        for (Integer item : commonItems) {
            dotProduct += user1Ratings.get(item) * user2Ratings.get(item);
        }

        double norm1 = Math.sqrt(user1Ratings.entrySet().stream()
                .filter(entry -> commonItems.contains(entry.getKey()))
                .mapToDouble(entry -> Math.pow(entry.getValue(), 2))
                .sum());

        double norm2 = Math.sqrt(user2Ratings.entrySet().stream()
                .filter(entry -> commonItems.contains(entry.getKey()))
                .mapToDouble(entry -> Math.pow(entry.getValue(), 2))
                .sum());

        return dotProduct / (norm1 * norm2);
    }



}
