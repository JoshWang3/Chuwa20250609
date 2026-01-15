package com.chuwa.redbook.exception;

/**
 * 当创建或更新资源时出现唯一性冲突（如用户名、邮箱或文章标题已存在），
 * 抛出此异常以返回 HTTP 409 Conflict。
 */
public class DuplicateResourceException extends RuntimeException {

    /**
     * 构造一个带有详细消息的异常
     * @param message 冲突资源的描述信息，如 “文章标题 'Spring Basics' 已存在”
     */
    public DuplicateResourceException(String message) {
        super(message);
    }

    /**
     * 可选：如果需要封装底层异常，可再加一个接受 cause 的构造器
     */
    public DuplicateResourceException(String message, Throwable cause) {
        super(message, cause);
    }
}
