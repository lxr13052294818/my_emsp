package com.volvo.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 描述:
 *
 * @author lxr
 * @date 2025/2/23
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RR<T> {

    private String code;    // 状态码，如 "success" 或 "error"
    private String msg;     // 消息描述
    private T data;         // 数据，泛型类型，可以是任何对象
    private Long total;     // 可选，记录总数，用于分页
    private Long timestamp; // 响应时间戳

    /**
     * 自定义成功的静态方法
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> RR<T> ok(T data) {
        return new RR<>("success", "Request successful", data, null, System.currentTimeMillis());
    }

    /**
     * 自定义失败的静态方法
     *
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> RR<T> fail(String msg) {
        return new RR<>("error", msg, null, null, System.currentTimeMillis());
    }

    /**
     * 自定义分页成功的响应方法
     *
     * @param data
     * @param total
     * @param <T>
     * @return
     */
    public static <T> RR<T> page(T data, Long total) {
        return new RR<>("success", "Request successful", data, total, System.currentTimeMillis());
    }
}
