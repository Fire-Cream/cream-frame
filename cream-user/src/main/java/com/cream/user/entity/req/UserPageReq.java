package com.cream.user.entity.req;

import lombok.Data;

/**
 * 用户分页 Req
 *
 * @author Cream
 * @since 2026-06-08 22:34
 */
@Data
public class UserPageReq {

    private Integer pageIndex;

    private Integer pageSize;
}
