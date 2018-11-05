
package org.drip.dynamics.hullwhite;

/*
 * -*- mode: java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 */

/*!
 * Copyright (C) 2019 Lakshmi Krishnamurthy
 * Copyright (C) 2018 Lakshmi Krishnamurthy
 * Copyright (C) 2017 Lakshmi Krishnamurthy
 * Copyright (C) 2016 Lakshmi Krishnamurthy
 * Copyright (C) 2015 Lakshmi Krishnamurthy
 * 
 *  This file is part of DROP, an open-source library targeting risk, transaction costs, exposure, margin
 *  	calculations, and portfolio construction within and across fixed income, credit, commodity, equity,
 *  	FX, and structured products.
 *  
 *  	https://lakshmidrip.github.io/DROP/
 *  
 *  DROP is composed of three main modules:
 *  
 *  - DROP Analytics Core - https://lakshmidrip.github.io/DROP-Analytics-Core/
 *  - DROP Portfolio Core - https://lakshmidrip.github.io/DROP-Portfolio-Core/
 *  - DROP Numerical Core - https://lakshmidrip.github.io/DROP-Numerical-Core/
 * 
 * 	DROP Analytics Core implements libraries for the following:
 * 	- Fixed Income Analytics
 * 	- Asset Backed Analytics
 * 	- XVA Analytics
 * 	- Exposure and Margin Analytics
 * 
 * 	DROP Portfolio Core implements libraries for the following:
 * 	- Asset Allocation Analytics
 * 	- Transaction Cost Analytics
 * 
 * 	DROP Numerical Core implements libraries for the following:
 * 	- Statistical Learning Library
 * 	- Numerical Optimizer Library
 * 	- Machine Learning Library
 * 	- Spline Builder Library
 * 
 * 	Documentation for DROP is Spread Over:
 * 
 * 	- Main                     => https://lakshmidrip.github.io/DROP/
 * 	- Wiki                     => https://github.com/lakshmiDRIP/DROP/wiki
 * 	- GitHub                   => https://github.com/lakshmiDRIP/DROP
 * 	- Javadoc                  => https://lakshmidrip.github.io/DROP/Javadoc/index.html
 * 	- Technical Specifications => https://github.com/lakshmiDRIP/DROP/tree/master/Docs/Internal
 * 	- Release Versions         => https://lakshmidrip.github.io/DROP/version.html
 * 	- Community Credits        => https://lakshmidrip.github.io/DROP/credits.html
 * 	- Issues Catalog           => https://github.com/lakshmiDRIP/DROP/issues
 * 	- JUnit                    => https://lakshmidrip.github.io/DROP/junit/index.html
 * 	- Jacoco                   => https://lakshmidrip.github.io/DROP/jacoco/index.html
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *   	you may not use this file except in compliance with the License.
 *   
 *  You may obtain a copy of the License at
 *  	http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  	distributed under the License is distributed on an "AS IS" BASIS,
 *  	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  
 *  See the License for the specific language governing permissions and
 *  	limitations under the License.
 */

/**
 * <i>ShortRateUpdate</i> records the Metrics associated with the Evolution of the Instantaneous Short Rate
 *  from a Starting to the Terminal Date.
 *  <ul>
 *		<li><b>Module</b>        = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/dynamics">Dynamics</a></li>
 *		<li><b>Package</b>       = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/dynamics/hullwhite">Hull White</a></li>
 *		<li><b>Specification</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/Docs/Internal/FixedIncome">Fixed Income Analytics</a></li>
 *  </ul>
 *
 * @author Lakshmi Krishnamurthy
 */

public class ShortRateUpdate extends org.drip.dynamics.evolution.LSQMPointUpdate {
	private double _dblExpectedFinalShortRate = java.lang.Double.NaN;
	private double _dblFinalShortRateVariance = java.lang.Double.NaN;
	private org.drip.state.identifier.FundingLabel _lslFunding = null;

	/**
	 * Construct an Instance of ShortRateUpdate
	 * 
	 * @param lslFunding The Funding Latent State Label
	 * @param iInitialDate The Initial Date
	 * @param iFinalDate The Final Date
	 * @param iTargetPointDate The Target Point Date
	 * @param dblInitialShortRate The Initial Short Rate
	 * @param dblRealizedFinalShortRate The Realized Final Short Rate
	 * @param dblExpectedFinalShortRate The Expected Final Short Rate
	 * @param dblFinalShortRateVariance The Final Variance of the Short Rate
	 * @param dblZeroCouponBondPrice The Zero Coupon Bond Price
	 * 
	 * @return The ShortRateUpdate Instance
	 * 
	 * @throws java.lang.Exception Thrown if the Inputs are Invalid
	 */

	public static final ShortRateUpdate Create (
		final org.drip.state.identifier.FundingLabel lslFunding,
		final int iInitialDate,
		final int iFinalDate,
		final int iTargetPointDate,
		final double dblInitialShortRate,
		final double dblRealizedFinalShortRate,
		final double dblExpectedFinalShortRate,
		final double dblFinalShortRateVariance,
		final double dblZeroCouponBondPrice)
		throws java.lang.Exception
	{
		org.drip.dynamics.evolution.LSQMPointRecord lrSnapshot = new
			org.drip.dynamics.evolution.LSQMPointRecord();

		if (!lrSnapshot.setQM (lslFunding,
			org.drip.analytics.definition.LatentStateStatic.DISCOUNT_QM_ZERO_RATE,
				dblRealizedFinalShortRate))
			return null;

		if (!lrSnapshot.setQM (lslFunding,
			org.drip.analytics.definition.LatentStateStatic.DISCOUNT_QM_DISCOUNT_FACTOR,
				dblZeroCouponBondPrice))
			return null;

		org.drip.dynamics.evolution.LSQMPointRecord lrIncrement = new
			org.drip.dynamics.evolution.LSQMPointRecord();

		if (!lrIncrement.setQM (lslFunding,
			org.drip.analytics.definition.LatentStateStatic.DISCOUNT_QM_ZERO_RATE,
				dblRealizedFinalShortRate - dblInitialShortRate))
			return null;

		try {
			return new ShortRateUpdate (lslFunding, iInitialDate, iFinalDate, iTargetPointDate, lrSnapshot,
				lrIncrement, dblExpectedFinalShortRate, dblFinalShortRateVariance);
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private ShortRateUpdate (
		final org.drip.state.identifier.FundingLabel lslFunding,
		final int iInitialDate,
		final int iFinalDate,
		final int iViewDate,
		final org.drip.dynamics.evolution.LSQMPointRecord lrSnapshot,
		final org.drip.dynamics.evolution.LSQMPointRecord lrIncrement,
		final double dblExpectedFinalShortRate,
		final double dblFinalShortRateVariance)
		throws java.lang.Exception
	{
		super (iInitialDate, iFinalDate, iViewDate, lrSnapshot, lrIncrement);

		if (null == (_lslFunding = lslFunding) || !org.drip.quant.common.NumberUtil.IsValid
			(_dblExpectedFinalShortRate = dblExpectedFinalShortRate) ||
				!org.drip.quant.common.NumberUtil.IsValid (_dblFinalShortRateVariance =
					dblFinalShortRateVariance)) {
			System.out.println (_lslFunding.fullyQualifiedName());

			System.out.println ("Final Short Rate: " + _dblExpectedFinalShortRate);

			System.out.println ("Final Short Rate Variance: " + _dblFinalShortRateVariance);

			throw new java.lang.Exception ("ShortRateUpdate ctr: Invalid Inputs!");
		}
	}

	/**
	 * Retrieve the Initial Short Rate
	 * 
	 * @return The Initial Short Rate
	 * 
	 * @throws java.lang.Exception Thrown if the Initial Short Rate is not available
	 */

	public double initialShortRate()
		throws java.lang.Exception
	{
		return realizedFinalShortRate() - shortRateIncrement();
	}

	/**
	 * Retrieve the Realized Final Short Rate
	 * 
	 * @return The Realized Final Short Rate
	 * 
	 * @throws java.lang.Exception Thrown if the Realized Final Short Rate is not available
	 */

	public double realizedFinalShortRate()
		throws java.lang.Exception
	{
		return snapshot().qm (_lslFunding,
			org.drip.analytics.definition.LatentStateStatic.DISCOUNT_QM_ZERO_RATE);
	}

	/**
	 * Retrieve the Short Rate Increment
	 * 
	 * @return The Short Rate Increment
	 * 
	 * @throws java.lang.Exception Thrown if the Short Rate Increment is not available
	 */

	public double shortRateIncrement()
		throws java.lang.Exception
	{
		return increment().qm (_lslFunding,
			org.drip.analytics.definition.LatentStateStatic.DISCOUNT_QM_ZERO_RATE);
	}

	/**
	 * Retrieve the Expected Final Short Rate
	 * 
	 * @return The Expected Final Short Rate
	 */

	public double expectedFinalShortRate()
	{
		return _dblExpectedFinalShortRate;
	}

	/**
	 * Retrieve the Final Short Rate Variance
	 * 
	 * @return The Final Short Rate Variance
	 */

	public double finalShortRateVariance()
	{
		return _dblFinalShortRateVariance;
	}

	/**
	 * Compute the Zero Coupon Bond Price
	 * 
	 * @param dblFinalInitialZeroRatio The Final-to-Initial Zero-Coupon Bond Price Ratio
	 * 
	 * @return The Zero Coupon Bond Price
	 * 
	 * @throws java.lang.Exception Thrown if the Zero Coupon Bond Price cannot be computed
	 */

	public double zeroCouponBondPrice (
		final double dblFinalInitialZeroRatio)
		throws java.lang.Exception
	{
		if (!org.drip.quant.common.NumberUtil.IsValid (dblFinalInitialZeroRatio))
			throw new java.lang.Exception ("ShortRateUpdate::zeroCouponBondPrice => Invalid Inputs");

		return dblFinalInitialZeroRatio * snapshot().qm (_lslFunding,
			org.drip.analytics.definition.LatentStateStatic.DISCOUNT_QM_DISCOUNT_FACTOR);
	}
}
