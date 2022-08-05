package com.bookkeeping.app.Utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author jilei
 * @date 2022-08-05
 */
@Data
@ToString
@NoArgsConstructor
public class AuthToken implements Serializable {
    String access_token;//访问token就是短令牌，用户身份令牌
    String refresh_token;//刷新token
    String jwt_token;//jwt令牌
}
