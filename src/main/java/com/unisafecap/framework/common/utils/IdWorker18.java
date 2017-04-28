package com.unisafecap.framework.common.utils;

/**
 * <P>
 * 获得长度18位数分布式自增ID,snowflake算法
 * <P>
 * 64位ID (42(毫秒)+5(机器ID)+5(业务编码)+12(重复累加)) snowflake的结构如下(每部分用-分开):
 * <P>
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000
 * <P>
 * 1位标识，由于long基本类型在Java中是带符号的，最高位是符号位，正数是0，负数是1，所以id一般是正数，最高位是0
 * <P>
 * 41位时间截，注意，41位时间截不是存储当前时间的时间截，而是存储时间截的差值（当前时间截 - 开始时间截 得到的值），这里的的开始时间截，一般是我们的id生成器开始使用的时间，由我们程序来指定的（如下下面程序IdWorker类的startTime属性）。
 * <P>
 * 41位的时间截，可以使用69年，年T = (1L << * 41) / (1000L * 60 * 60 * 24 * 365) = 69
 * <P>
 * 10位的数据机器位，可以部署在1024个节点
 * <P>
 * 12位序列，同一时间截，同一机器，可以生成4096个id
 * <P>
 * 
 * @author 左通
 * @since 2017年1月12日
 * @version V1.0
 */
public class IdWorker18 {
	private final static long twepoch = 1288834974657L;
	// 机器标识位数
	private final static long workerIdBits = 5L;
	// 数据中心标识位数
	private final static long datacenterIdBits = 5L;
	// 机器ID最大值
	private final static long maxWorkerId = -1L ^ (-1L << workerIdBits);
	// 数据中心ID最大值
	private final static long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
	// 毫秒内自增位
	private final static long sequenceBits = 12L;
	// 机器ID偏左移12位
	private final static long workerIdShift = sequenceBits;
	// 数据中心ID左移17位
	private final static long datacenterIdShift = sequenceBits + workerIdBits;
	// 时间毫秒左移22位
	private final static long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

	private final static long sequenceMask = -1L ^ (-1L << sequenceBits);

	private static long lastTimestamp = -1L;

	private long sequence = 0L;
	private final long workerId;
	private final long datacenterId;

	/**
	 * @param workerId
	 *            机器ID
	 * @param datacenterId
	 *            业务编码
	 */
	public IdWorker18(long workerId, long datacenterId) {
		if (workerId > maxWorkerId || workerId < 0) {
			throw new IllegalArgumentException("worker Id can't be greater than %d or less than 0");
		}
		if (datacenterId > maxDatacenterId || datacenterId < 0) {
			throw new IllegalArgumentException("datacenter Id can't be greater than %d or less than 0");
		}
		this.workerId = workerId;
		this.datacenterId = datacenterId;
	}

	/**
	 * 获得分布式自增ID
	 * 
	 * @return long
	 */
	public synchronized long nextId() {
		long timestamp = timeGen();
		if (timestamp < lastTimestamp) {
			try {
				throw new Exception("Clock moved backwards.  Refusing to generate id for " + (lastTimestamp - timestamp) + " milliseconds");
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (lastTimestamp == timestamp) {
			// 当前毫秒内，则+1
			sequence = (sequence + 1) & sequenceMask;
			if (sequence == 0) {
				// 当前毫秒内计数满了，则等待下一秒
				timestamp = tilNextMillis(lastTimestamp);
			}
		} else {
			sequence = 0;
		}
		lastTimestamp = timestamp;
		// ID偏移组合生成最终的ID，并返回ID
		long nextId = ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;

		return nextId;
	}

	private long tilNextMillis(final long lastTimestamp) {
		long timestamp = this.timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = this.timeGen();
		}
		return timestamp;
	}

	private long timeGen() {
		return System.currentTimeMillis();
	}
}