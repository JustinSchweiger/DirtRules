package net.dirtcraft.plugins.dirtrules.utils.gradient;

@FunctionalInterface
public interface Interpolator {

	double[] interpolate(double from, double to, int max);
}