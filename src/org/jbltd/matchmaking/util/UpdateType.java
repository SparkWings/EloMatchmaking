package org.jbltd.matchmaking.util;

public enum UpdateType
{
	FIVESEC(5000),
	TWOSEC(2000),
	SEC(1000),
	FAST(500),
	TICK(49);

	private long _time;
	private long _last;
	private long _timeSpent;
	private long _timeCount;

	UpdateType(long time)
	{
		_time = time;
		_last = System.currentTimeMillis();
	}

	public boolean Elapsed()
	{
		if (elapsed(_last, _time))
		{
			_last = System.currentTimeMillis();
			return true;
		}

		return false;
	}

	public void StartTime()
	{
		_timeCount = System.currentTimeMillis();
	}

	public void StopTime()
	{
		_timeSpent += System.currentTimeMillis() - _timeCount;
	}

	public void PrintAndResetTime()
	{
		System.out.println(this.name() + " in a second: " + _timeSpent);
		_timeSpent = 0;
	}

	public static boolean elapsed(long from, long required)
	{
		return System.currentTimeMillis() - from > required;
	}

}