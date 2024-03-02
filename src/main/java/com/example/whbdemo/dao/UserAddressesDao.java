package com.example.whbdemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.whbdemo.domain.UserAddresses;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
@Transactional
public interface UserAddressesDao extends BaseMapper<UserAddresses> {
    @Select("SELECT a.* FROM user_addresses a JOIN user u ON a.user_id = u.user_id WHERE u.account = #{account};")
    List<UserAddresses> addresserusermsg(String account);

    @Delete("DELETE FROM user_addresses WHERE address_id = #{addressId}")
    int deleteAddressById(Integer addressId);

    @Update("UPDATE user_addresses SET  recipient = #{Recipient},  address = #{Address},  postal_code = #{postalCode},  phone_number = #{phoneNumber} WHERE address_id = #{addressId};")
    int updateusermsg (UserAddresses userAddresses);

    @Insert("INSERT INTO user_addresses (user_id, recipient, address, postal_code, phone_number) VALUES (#{userId},#{Recipient},#{Address},#{postalCode},#{phoneNumber});")
    int insertusermsg (UserAddresses userAddresses);

}
