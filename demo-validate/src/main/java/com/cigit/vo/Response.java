package com.cigit.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author:zhuzhou
 * @Date: 2019/11/19  9:34
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private Integer code;
    private String message;



}
