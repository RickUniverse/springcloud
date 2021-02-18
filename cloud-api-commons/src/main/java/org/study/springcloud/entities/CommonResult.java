package org.study.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lijichen
 * @date 2021/2/11 - 19:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    // 404 not found
    private Integer code;
    private String message;
    private T       data;

    public CommonResult(Integer code,String message){
        this(code,message,null);
    }
}