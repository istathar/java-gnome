package org.freedesktop.cairo;

import org.freedesktop.bindings.Constant;

/**
 * Used to specify the filtering that should be applied when reading pixel
 * values from patterns. See {@link Pattern#setFilter(Filter)} for specifying
 * the desired filter to be used with a particular pattern.
 * 
 * @author Will Temperley
 */
public class Filter extends Constant
{
    private Filter(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * A high-performance filter, with quality similar to
     * {@link Filter#NEAREST}
     */
    public static final Filter FAST = new Filter(CairoFilter.FAST, "FAST");

    /**
     * A reasonable-performance filter, with quality similar to
     * {@link Filter#BILINEAR}
     */
    public static final Filter GOOD = new Filter(CairoFilter.GOOD, "GOOD");

    /**
     * The highest-quality available, performance may not be suitable for
     * interactive use
     */
    public static final Filter BEST = new Filter(CairoFilter.BEST, "BEST");

    /**
     * Nearest-neighbour filtering
     */
    public static final Filter NEAREST = new Filter(CairoFilter.NEAREST, "NEAREST");

    /**
     * Linear interpolation in two dimensions
     */
    public static final Filter BILINEAR = new Filter(CairoFilter.BILINEAR, "BILINEAR");

    /**
     * This filter value is currently unimplemented, and should not be used in
     * current code.
     */
    public static final Filter GAUSSIAN = new Filter(CairoFilter.GAUSSIAN, "GAUSSIAN");

}
