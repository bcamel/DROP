
package org.drip.spline.params;

import org.drip.numerical.common.NumberUtil;

/*
 * -*- mode: java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 */

/*!
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
 * <i>SegmentResponseValueConstraint</i> holds the following set of fields that characterize a single global
 * linear constraint between the predictor and the response variables within a single segment, expressed
 * linearly across the constituent nodes. Constraints are expressed as
 * 
 * 			C = Sigma_j [W_j * y_j] = V where
 * 
 * 	x_j - Predictor j
 * 	y_j - Response j
 * 	W_j - Weight at ordinate j
 * 	V - Value of the Constraint
 * 
 * SegmentResponseValueConstraint exports the following functionality:
 *
 * <br>
 *  <ul>
 * 		<li>Indicator specifying that the knot is to the left of the constraint ordinates</li>
 * 		<li>Indicator specifying that the knot is to the right of the constraint ordinates</li>
 * 		<li>Indicator specifying that the knot splits the constraint ordinates</li>
 * 		<li>Generate a SegmentResponseValueConstraint instance from the given predictor/response pair</li>
 * 		<li>SegmentResponseValueConstraint constructor</li>
 * 		<li>Retrieve the Array of Predictor Ordinates</li>
 * 		<li>Retrieve the Array of Response Weights at each Predictor Ordinate</li>
 * 		<li>Retrieve the Constraint Value</li>
 * 		<li>Display the Comment Annotated State</li>
 * 		<li>Convert the Segment Constraint onto Local Predictor Ordinates, the corresponding Response Basis Function, and the Shape Controller Realizations</li>
 * 		<li>Get the Position of the Predictor Knot relative to the Constraints</li>
 *  </ul>
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
 *		<tr><td><b>Package</b></td> <td><a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/spline/params/README.md">Spline Segment Construction Control Parameters</a></td></tr>
 *  </table>
 *  <br>
 * 
 * @author Lakshmi Krishnamurthy
 */

public class SegmentResponseValueConstraint
{

	/**
	 * Indicator specifying that the knot is to the left of the constraint ordinates
	 */

	public static final int LEFT_OF_CONSTRAINT = 1;

	/**
	 * Indicator specifying that the knot is to the right of the constraint ordinates
	 */

	public static final int RIGHT_OF_CONSTRAINT = 2;

	/**
	 * Indicator specifying that the knot splits the constraint ordinates
	 */

	public static final int SPLITS_CONSTRAINT = 4;

	private double[] _predictorOrdinateArray = null;
	private double[] _responseValueWeightArray = null;
	private double _weightedResponseValueConstraint = Double.NaN;

	/**
	 * Generate a SegmentResponseValueConstraint instance from the given predictor/response pair.
	 * 
	 * @param predictorOrdinate The Predictor Ordinate
	 * @param responseValue The Response Value
	 * 
	 * @return The SegmentResponseValueConstraint instance
	 */

	public static final SegmentResponseValueConstraint FromPredictorResponsePair (
		final double predictorOrdinate,
		final double responseValue)
	{
		try {
			return !NumberUtil.IsValid (predictorOrdinate) || !NumberUtil.IsValid (responseValue) ? null :
				new SegmentResponseValueConstraint (
					new double[] {predictorOrdinate},
					new double[] {1.},
					responseValue
				);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * SegmentResponseValueConstraint constructor
	 * 
	 * @param adblPredictorOrdinate The Array of Global Predictor Ordinates
	 * @param adblResponseValueWeight The Array of the Weights to be applied to the Response at each
	 *  Predictor Ordinate
	 * @param dblWeightedResponseValueConstraint The Value of the Weighted Response Value Constraint
	 * 
	 * @throws java.lang.Exception Throws if the Inputs are Invalid
	 */

	public SegmentResponseValueConstraint (
		final double[] adblPredictorOrdinate,
		final double[] adblResponseValueWeight,
		final double dblWeightedResponseValueConstraint)
		throws java.lang.Exception
	{
		if (null == (_predictorOrdinateArray = adblPredictorOrdinate) || null == (_responseValueWeightArray =
			adblResponseValueWeight) || !org.drip.numerical.common.NumberUtil.IsValid
				(_weightedResponseValueConstraint = dblWeightedResponseValueConstraint))
			throw new java.lang.Exception ("SegmentResponseValueConstraint ctr: Invalid Inputs");

		int iNumPredictorOrdinate = adblPredictorOrdinate.length;

		if (0 == iNumPredictorOrdinate || _responseValueWeightArray.length != iNumPredictorOrdinate)
			throw new java.lang.Exception ("SegmentResponseValueConstraint ctr: Invalid Inputs");
	}

	/**
	 * Retrieve the Array of Predictor Ordinates
	 * 
	 * @return The Array of Predictor Ordinates
	 */

	public double[] predictorOrdinates()
	{
		return _predictorOrdinateArray;
	}

	/**
	 * Retrieve the Array of Response Weights at each Predictor Ordinate
	 * 
	 * @return The Array of Response Weights at each Predictor Ordinate
	 */

	public double[] responseWeights()
	{
		return _responseValueWeightArray;
	}

	/**
	 * Retrieve the Constraint Value
	 * 
	 * @return The Constraint Value
	 */

	public double constraintValue()
	{
		return _weightedResponseValueConstraint;
	}

	/**
	 * Display the Comment Annotated State
	 * 
	 * @param strComment Annotation Comment
	 */

	public void display (
		final java.lang.String strComment)
	{
		for (int i = 0; i < _predictorOrdinateArray.length; ++i)
			System.out.println ("\t\t" + strComment + " - " + new org.drip.analytics.date.JulianDate ((int)
				_predictorOrdinateArray[i]) + " => " + _responseValueWeightArray[i]);

		System.out.println ("\tConstraint: " + _weightedResponseValueConstraint);
	}

	/**
	 * Convert the Segment Constraint onto Local Predictor Ordinates, the corresponding Response Basis
	 *  Function, and the Shape Controller Realizations
	 * 
	 * @param lbe The Local Basis Evaluator
	 * @param ics Inelastics transformer to convert coordinate space to Local from Global
	 * 
	 * @return The Segment Basis Function Constraint
	 */

	public org.drip.spline.params.SegmentBasisFlexureConstraint responseIndexedBasisConstraint (
		final org.drip.spline.segment.BasisEvaluator lbe,
		final org.drip.spline.segment.LatentStateInelastic ics)
	{
		if (null == lbe || null == ics) return null;

		int iNumResponseBasis = lbe.numBasis();

		int iNumPredictorOrdinate = _predictorOrdinateArray.length;
		double[] adblResponseBasisWeight = new double[iNumResponseBasis];

		if (0 == iNumResponseBasis) return null;

		try {
			for (int i = 0; i < iNumResponseBasis; ++i) {
				adblResponseBasisWeight[i] = 0.;

				for (int j = 0; j < iNumPredictorOrdinate; ++j)
					adblResponseBasisWeight[i] += _responseValueWeightArray[j] *
						lbe.shapedBasisFunctionResponse (_predictorOrdinateArray[j], i);
			}

			return new org.drip.spline.params.SegmentBasisFlexureConstraint (adblResponseBasisWeight,
					_weightedResponseValueConstraint);
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Get the Position of the Predictor Knot relative to the Constraints
	 * 
	 * @param dblPredictorKnot The Predictor Knot Ordinate
	 * 
	 * @return Indicator specifying whether the Knot is Left of the constraints, Right of the Constraints, or
	 *  splits the Constraints
	 * 
	 * @throws java.lang.Exception Thrown if Inputs are invalid
	 */

	public int knotPosition (
		final double dblPredictorKnot)
		throws java.lang.Exception
	{
		if (!org.drip.numerical.common.NumberUtil.IsValid (dblPredictorKnot))
			throw new java.lang.Exception ("SegmentResponseValueConstraint::knotPosition => Invalid Inputs");

		if (dblPredictorKnot < _predictorOrdinateArray[0]) return LEFT_OF_CONSTRAINT;

		if (dblPredictorKnot > _predictorOrdinateArray[_predictorOrdinateArray.length - 1])
			return RIGHT_OF_CONSTRAINT;

		return SPLITS_CONSTRAINT;
	}
}
