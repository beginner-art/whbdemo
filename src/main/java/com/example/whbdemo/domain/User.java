package com.example.whbdemo.domain;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @TableId(value = "user_id")
    private String user_id;
    private String account;
    private String username;
    private String password;
    private String phone_number;
    private String role;
    private Integer points;
    //逻辑删除,1标记删除，0未删除
    @TableLogic(value = "0", delval = "1")
    private Integer deleted;
}
