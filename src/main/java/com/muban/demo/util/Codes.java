package com.muban.demo.util;

public class Codes {

    /**
     * 正数 ~ -99为通用码 ，其他业务顺延（根据业务划分，每个业务独占100空间）
     */
    public interface Common {
        ICode OK = new Code(200, "success");
        ICode ERROR = new Code(500, "server error");
    }

    /**
     * 在这里之前补充新的业务码
     */
    public interface Custom {
        ICode NOT_LOGIN = new Code(401, "please login");

        ICode NO_AUTH = new Code(403, "无权访问");

        ICode PARAM_ERROR = new Code(411, "param error");

        ICode SMS_ERROR = new Code(412, "验证码不正确");

        ICode API_RUNNING = new Code(413, "正在运行中");

        ICode DEVICEID_CHECK_ERROR = new Code(424, "deviceId 验证失败");

        ICode ERROR_LOGIC = new Code(5001, "错误逻辑");

    }


    public interface GameReservation {

        ICode RESERVATION_NOT_START = new Code(5001, "reservation not start");
        ICode RESERVATION_ENDED = new Code(5002, "reservation is end");
    }
    /**
     * trade服务的状态码,1000~1999区段
     */
    public interface Trade {
        ICode TRADE_COMPLETE = new Code(1001, "trade has been completed");
        ICode TRADE_ORDER_INEXIST = new Code(1002, "order in-existence");
        ICode TRADE_ORDER_REVIEWED = new Code(1003, "order has been reviewed");
        ICode TRADE_AUTHOR_OFF = new Code(1004, "author has been closed");
        ICode TRADE_BLOG_CANNOT_REWARD = new Code(1005, "blog can not be rewarded");
    }

    /**
     * activity服务的状态码,2000~2999区段
     */
    public interface Activity {
        //hao123萌活动
        ICode HAO_13_OK = new Code(0, "ok");
        ICode HAO_13_FAIL = new Code(-1, "fail");
        ICode HAO_13_TIME_OUT = new Code(-2, "request time out");
        ICode HAO_13_INVALID_REQUEST = new Code(-3, "invalid request");
        ICode HAO_13_UNKONWN_ERROR = new Code(-4, "unknown error");

        ICode ACT_END = new Code(421, "活动已结束");
        ICode ACT_UNSTART = new Code(422, "活动未开始");
        ICode CHANCEL_OVER = new Code(423, "点亮机会用完");

        ICode PARALLEL_WORLD_VOTE_OVER = new Code(2100, "投币超出上限");
        ICode PARALLEL_WORLD_VOTE_SHORTAGE = new Code(2101, "余额不足");

    }

    public static Code copy(ICode source){
        return new Code(source.getCode(), source.getMsg());
    }

    public static Code selfCode(int code, String msg){
        return new Code(code, msg);
    }

    private static class Code implements ICode {
        private static final long serialVersionUID = -3950509458439922599L;
        private int code;
        private String msg;

        private Code(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String getMsg() {
            return msg;
        }

        @Override
        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}