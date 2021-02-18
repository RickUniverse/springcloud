package org.study.springcloud.alibaba.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lijichen
 * @date 2021/2/18 - 18:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Storage {

    private Long id;

    private Long productId;

    private Integer total;

    private Integer used;

    private Integer residue;
}
