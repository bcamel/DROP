
package org.drip.spline.bspline;

import org.drip.numerical.common.NumberUtil;

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
 * Copyright (C) 2013 Lakshmi Krishnamurthy
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
 * <i>RightHatShapeControl</i> implements the BasisHatShapeControl interface for the right hat basis set as
 * 	laid out in the basic framework outlined in Koch and Lyche (1989), Koch and Lyche (1993), and Kvasov
 * 	(2000) Papers.
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
 *		<tr><td><b>Project</b></td> <td><a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/spline/README.md">Basis Splines and Linear Compounders across a Broad Family of Spline Basis Functions</a></td></tr>
 *		<tr><td><b>Package</b></td> <td><a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/spline/bspline/README.md">de Boor Rational/Exponential/Tension B-Splines</a></td></tr>
 *  </table>
 *  <br>
 *
 * @author Lakshmi Krishnamurthy
 */

public class RightHatShapeControl
	extends BasisHatShapeControl
{

	/**
	 * <i>RightHatShapeControl</i> constructor
	 * 
	 * @param leftPredictorOrdinate The Left Predictor Ordinate
	 * @param rightPredictorOrdinate The Right Predictor Ordinate
	 * @param shapeControlType Type of the Shape Controller to be used - NONE, LINEAR/QUADRATIC Rational
	 * @param tension Tension of the Tension Hat Function
	 * 
	 * @throws Exception Thrown if the input is invalid
	 */

	public RightHatShapeControl (
		final double leftPredictorOrdinate,
		final double rightPredictorOrdinate,
		final String shapeControlType,
		final double tension)
		throws Exception
	{
		super (leftPredictorOrdinate, rightPredictorOrdinate, shapeControlType, tension);
	}

	@Override public double evaluate (
		final double predictorOrdinate)
		throws Exception
	{
		if (!in (predictorOrdinate)) {
			return 0.;
		}

		double left = left();

		double right = right();

		double tension = tension();

		String shapeControlType = shapeControlType();

		double width = right - left;
		double scale = 1. / (width * (6. + 6. * tension * width + 2. * tension * width * width));

		if (SHAPE_CONTROL_RATIONAL_LINEAR.equalsIgnoreCase (shapeControlType)) {
			return scale / (1. + tension * (predictorOrdinate - left));
		}

		if (SHAPE_CONTROL_RATIONAL_QUADRATIC.equalsIgnoreCase (shapeControlType)) {
			return scale / (1. + tension * (right - predictorOrdinate) * (predictorOrdinate - left) / width);
		}

		return (
			Math.exp (-tension * (predictorOrdinate - left))
		) / (width * (6. + 6. * tension * width + tension * width * width));
	}

	@Override public double derivative (
		final double predictorOrdinate,
		final int order)
		throws Exception
	{
		if (0 >= order) {
			throw new Exception ("RightHatShapeControl::derivative => Invalid Inputs");
		}

		double tension = tension();

		if (!in (predictorOrdinate) || 0. == tension) {
			return 0.;
		}

		double left = left();

		double width = right() - left;

		String shapeControlType = shapeControlType();

		if (SHAPE_CONTROL_RATIONAL_LINEAR.equalsIgnoreCase (shapeControlType)) {
			return (
				NumberUtil.Factorial (order) * Math.pow (-tension, order) * Math.pow (
					1. + tension * (predictorOrdinate - left), -order - 1
				)
			) / (width * (6. + 6. * tension * width + 2. * tension * width * width));
		}

		if (SHAPE_CONTROL_RATIONAL_EXPONENTIAL.equalsIgnoreCase (shapeControlType)) {
			return (
				Math.pow (-tension, order) * Math.exp (-tension * (predictorOrdinate - left))
			) / (width * (6. + 6. * tension * width + tension * width * width));
		}

		return super.derivative (predictorOrdinate, order);
	}

	@Override public double integrate (
		final double begin,
		final double end)
		throws Exception
	{
		if (!NumberUtil.IsValid (begin) || !NumberUtil.IsValid (end)) {
			throw new Exception ("RightHatShapeControl::integrate => Invalid Inputs");
		}

		double left = left();

		double right = right();

		double boundedEnd = NumberUtil.Bound (end, left, right);

		double boundedBegin = NumberUtil.Bound (begin, left, right);

		if (boundedBegin >= boundedEnd) {
			return 0.;
		}

		double tension = tension();

		if (0. == tension) {
			return boundedEnd - boundedBegin;
		}

		double width = right - left;

		String shapeControlType = shapeControlType();

		if (SHAPE_CONTROL_RATIONAL_LINEAR.equalsIgnoreCase (shapeControlType)) {
			return (
				Math.log ((1. + tension * (boundedEnd - left)) / (1. + tension * (boundedBegin - left)))
			) / tension / (width * (6. + 6. * tension * width + 2. * tension * width * width));
		}

		if (SHAPE_CONTROL_RATIONAL_EXPONENTIAL.equalsIgnoreCase (shapeControlType)) {
			return (
				Math.exp (-tension * (boundedBegin - left)) - Math.exp (-tension * (boundedEnd - left))
			) / tension / (width * (6. + 6. * tension * width + tension() * width * width));
		}

		return super.integrate (boundedBegin, boundedEnd);
	}

	@Override public double normalizer()
		throws Exception
	{
		double left = left();

		double right = right();

		double tension = tension();

		double width = right - left;

		String shapeControlType = shapeControlType();

		if (0. == tension) {
			return width;
		}

		if (SHAPE_CONTROL_RATIONAL_LINEAR.equalsIgnoreCase (shapeControlType)) {
			return (Math.log ((1. + tension * width))) / tension / (
				width * (6. + 6. * tension * width + 2. * tension * width * width)
			);
		}

		if (SHAPE_CONTROL_RATIONAL_EXPONENTIAL.equalsIgnoreCase (shapeControlType)) {
			return (1. - Math.exp (-tension * width)) / tension / (
				width * (6. + 6. * tension * width + tension * width * width)
			);
		}

		return super.integrate (left, right);
	}
}
