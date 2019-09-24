package com.ningxia.ensure.common;


public class IdUtil {
    //private long workerId;
    private static long sequence = 1L;
    private static long twepoch = 1513050642378L;

    private static long workerIdBits = 5L;
    private static long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private static long sequenceBits = 10L;
    private static long workerIdShift = sequenceBits;
    private static long timestampLeftShift = sequenceBits + workerIdBits;
    private static long sequenceMask = -1L ^ (-1L << sequenceBits);
    private static long lastTimestamp = -1L;

    //后缀用于集群部署时区别机器
    public synchronized static String nextId(String suffix) {
        long workerId = 1;
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("workerId不能大于 %d 不能小于 0", maxWorkerId));
        }
        long timestamp = timeGen();

        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("系统时间错误， %d 毫秒内不可生成id",
                    lastTimestamp - timestamp));
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }

        lastTimestamp = timestamp;
        Long id = ((timestamp - twepoch) << timestampLeftShift) |
                (workerId << workerIdShift) |
                sequence;
        return id.toString() + suffix;
    }

    private static long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private static long timeGen() {
        return System.currentTimeMillis();
    }

    //---------------Test---------------
//    public static void main(String[] args) {
//        Set<String> set = new HashSet<>();
//        for (int i = 0; i < 10; i++) {
//            String id = IdUtil.nextId(1);
//            System.out.println(id);
//            if (!set.add(id)) {
//                System.out.println("重复" + id);
//            }
//        }
//        System.out.println(IdUtil.nextId(1));
//        Map map = new HashMap();
//    }
}
