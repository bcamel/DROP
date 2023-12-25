
package org.drip.state.curve;

import org.drip.function.definition.R1ToR1;
import org.drip.numerical.common.NumberUtil;
import org.drip.spline.grid.Span;
import org.drip.state.identifier.CustomLabel;
import org.drip.state.volatility.VolatilityCurve;

/*
 * -*- mode: java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 */

/*!
 * Copyright (C) 2025 Lakshmi Krishnamurthy
 * Copyright (C) 2024 Lakshmi Krishnamurthy
 * Copyright (C) 2023 Lakshmi Krishnamurthy
 * Copyright (C) 2022 Lakshmi Krishnamurthy
 * Copyright (C) 2021 Lakshmi Krishnamurthy
 * Copyright (C) 2020 Lakshmi Krishnamurthy
 * Copyright (C) 2019 Lakshmi Krishnamurthy
 * Copyright (C) 2018 Lakshmi Krishnamurthy
 * Copyright (C) 2017 Lakshmi Krishnamurthy
 * Copyright (C) 2016 Lakshmi Krishnamurthy
 * Copyright (C) 2015 Lakshmi Krishnamurthy
 * Copyright (C) 2014 Lakshmi Krishnamurthy
 * 
 *  This file is part of DROP, an open-source library targeting analytics/risk, transaction cost analytics,
 *  	asset liability management analytics, capital, exposure, and margin analytics, valuation adjustment
 *  	analytics, and portfolio construction analytics within and across fixed income, credit, commodity,
 *  	equity, FX, and structured products. It also includes auxiliary libraries for algorithm support,
 *  	numerical analysis, numerical optimization, spline builder, model validation, statistical learning,
 *  	graph builder/navigator, and computational support.
 *  
 *  	https://lakshmidrip.github.io/DROP/
 *  
 *  DROP is composed of three modules:
 *  
 *  - DROP Product Core - https://lakshmidrip.github.io/DROP-Product-Core/
 *  - DROP Portfolio Core - https://lakshmidrip.github.io/DROP-Portfolio-Core/
 *  - DROP Computational Core - https://lakshmidrip.github.io/DROP-Computational-Core/
 * 
 * 	DROP Product Core implements libraries for the following:
 * 	- Fixed Income Analytics
 * 	- Loan Analytics
 * 	- Transaction Cost Analytics
 * 
 * 	DROP Portfolio Core implements libraries for the following:
 * 	- Asset Allocation Analytics
 *  - Asset Liability Management Analytics
 * 	- Capital Estimation Analytics
 * 	- Exposure Analytics
 * 	- Margin Analytics
 * 	- XVA Analytics
 * 
 * 	DROP Computational Core implements libraries for the following:
 * 	- Algorithm Support
 * 	- Computation Support
 * 	- Function Analysis
 *  - Graph Algorithm
 *  - Model Validation
 * 	- Numerical Analysis
 * 	- Numerical Optimizer
 * 	- Spline Builder
 *  - Statistical Learning
 * 
 * 	Documentation for DROP is Spread Over:
 * 
 * 	- Main                     => https://lakshmidrip.github.io/DROP/
 * 	- Wiki                     => https://github.com/lakshmiDRIP/DROP/wiki
 * 	- GitHub                   => https://github.com/lakshmiDRIP/DROP
 * 	- Repo Layout Taxonomy     => https://github.com/lakshmiDRIP/DROP/blob/master/Taxonomy.md
 * 	- Javadoc                  => https://lakshmidrip.github.io/DROP/Javadoc/index.html
 * 	- Technical Specifications => https://github.com/lakshmiDRIP/DROP/tree/master/Docs/Internal
 * 	- Release Versions         => https://lakshmidrip.github.io/DROP/version.html
 * 	- Community Credits        => https://lakshmidrip.github.io/DROP/credits.html
 * 	- Issues Catalog           => https://github.com/lakshmiDRIP/DROP/issues
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
 * <i>BasisSplineDeterministicVolatility</i> extends the BasisSplineTermStructure for the specific case of
 * the Implementation of the Deterministic Volatility Term Structure.
 *
 *  <br>
 *  <style>table, td, th {
 *  	padding: 1px; border: 2px solid #008000; border-radius: 8px; background-color: #dfff00;
 *		text-align: center; color:  #0000ff;
 *  }
 *  </style>
 *  
 *  <table style="border:1px solid black;margin-left:auto;margin-right:auto;">
 *		<tr><td><b>Module </b></td> <td><a href = "https://github.com/lakshmiDRIP/DROP/tree/master/ProductCore.md">Product Core Module</a></td></tr>
 *		<tr><td><b>Library</b></td> <td><a href = "https://github.com/lakshmiDRIP/DROP/tree/master/FixedIncomeAnalyticsLibrary.md">Fixed Income Analytics</a></td></tr>
 *		<tr><td><b>Project</b></td> <td><a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/state/README.md">Latent State Inference and Creation Utilities</a></td></tr>
 *		<tr><td><b>Package</b></td> <td><a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/state/curve/README.md">Basis Spline Based Latent States</a></td></tr>
 *  </table>
 *
 * @author Lakshmi Krishnamurthy
 */

public class BasisSplineDeterministicVolatility extends VolatilityCurve
{
	private Span _impliedVolatilitySpan = null;

	/**
	 * BasisSplineDeterministicVolatility Constructor
	 * 
	 * @param epochDate The Epoch Date
	 * @param customLabel Latent State Label
	 * @param currency The Currency
	 * @param impliedVolatilitySpan The Implied Volatility Span
	 * 
	 * @throws Exception Thrown if the Inputs are Invalid
	 */

	public BasisSplineDeterministicVolatility (
		final int epochDate,
		final CustomLabel customLabel,
		final String currency,
		final Span impliedVolatilitySpan)
		throws Exception
	{
		super (epochDate, customLabel, currency);

		if (null == (_impliedVolatilitySpan = impliedVolatilitySpan)) {
			throw new Exception ("BasisSplineDeterministicVolatility ctr: Invalid Inputs");
		}
	}

	@Override public double impliedVol (
		final int date)
		throws Exception
	{
		double spanLeft = _impliedVolatilitySpan.left();

		if (spanLeft >= date) {
			return _impliedVolatilitySpan.calcResponseValue (spanLeft);
		}

		double spanRight = _impliedVolatilitySpan.right();

		if (spanRight <= date) {
			return _impliedVolatilitySpan.calcResponseValue (spanRight);
		}

		return _impliedVolatilitySpan.calcResponseValue (date);
	}

	@Override public double node (
		final int date)
		throws Exception
	{
		double impliedVolatility = impliedVol (date);

		return Math.sqrt (
			impliedVolatility * impliedVolatility + 2. * impliedVolatility * (
				date - epoch().julian()
			) / 365.25 * _impliedVolatilitySpan.calcResponseValueDerivative (date, 1)
		);
	}

	@Override public double vol (
		final int date)
		throws Exception
	{
		return node (date);
	}

	@Override public double nodeDerivative (
		final int date,
		final int order)
		throws Exception
	{
		if (!NumberUtil.IsValid (date)) {
			throw new Exception ("BasisSplineDeterministicVolatility::nodeDerivative => Invalid Inputs");
		}

		return new R1ToR1 (null) {
			@Override public double evaluate (
				double x)
				throws Exception
			{
				return node ((int) x);
			}
		}.derivative (date, order);
	}
}
