
package org.drip.specialfunction.definition;

/*
 * -*- mode: java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 */

/*!
 * Copyright (C) 2022 Lakshmi Krishnamurthy
 * Copyright (C) 2021 Lakshmi Krishnamurthy
 * Copyright (C) 2020 Lakshmi Krishnamurthy
 * Copyright (C) 2019 Lakshmi Krishnamurthy
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
 * <i>ModifiedScaledExponentialEstimator</i> exposes the Estimator for the Modified Scaled Exponential
 * Function. The References are:
 * 
 * <br><br>
 * 	<ul>
 * 		<li>
 * 			Gradshteyn, I. S., I. M. Ryzhik, Y. V. Geronimus, M. Y. Tseytlin, and A. Jeffrey (2015):
 * 				<i>Tables of Integrals, Series, and Products</i> <b>Academic Press</b>
 * 		</li>
 * 		<li>
 * 			Hilfer, J. (2002): H-function Representations for Stretched Exponential Relaxation and non-Debye
 * 				Susceptibilities in Glassy Systems <i>Physical Review E</i> <b>65 (6)</b> 061510
 * 		</li>
 * 		<li>
 * 			Wikipedia (2019): Stretched Exponential Function
 * 				https://en.wikipedia.org/wiki/Stretched_exponential_function
 * 		</li>
 * 		<li>
 * 			Wuttke, J. (2012): Laplace-Fourier Transform of the Stretched Exponential Function: Analytic
 * 				Error-Bounds, Double Exponential Transform, and Open Source Implementation <i>libkw</i>
 * 				<i>Algorithm</i> <b>5 (4)</b> 604-628
 * 		</li>
 * 		<li>
 * 			Zorn, R. (2002): Logarithmic Moments of Relaxation Time Distributions <i>Journal of Chemical
 * 				Physics</i> <b>116 (8)</b> 3204-3209
 * 		</li>
 * 	</ul>
 *
 *	<br><br>
 *  <ul>
 *		<li><b>Module </b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/ComputationalCore.md">Computational Core Module</a></li>
 *		<li><b>Library</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/FunctionAnalysisLibrary.md">Function Analysis Library</a></li>
 *		<li><b>Project</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/specialfunction/README.md">Special Function Implementation Analysis</a></li>
 *		<li><b>Package</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/specialfunction/definition/README.md">Definition of Special Function Estimators</a></li>
 *  </ul>
 *
 * @author Lakshmi Krishnamurthy
 */

public class ModifiedScaledExponentialEstimator extends org.drip.function.definition.R1ToR1
{
	private double _characteristicRelaxationTime = java.lang.Double.NaN;
	private org.drip.function.definition.R1ToR1 _exponentFunction = null;

	/**
	 * ModifiedScaledExponentialEstimator Constructor
	 * 
	 * @param exponentFunction The Exponent Function
	 * @param characteristicRelaxationTime The Characteristic Relaxation Time
	 * 
	 * @throws java.lang.Exception Thrown if the Inputs are Invalid
	 */

	public ModifiedScaledExponentialEstimator (
		final org.drip.function.definition.R1ToR1 exponentFunction,
		final double characteristicRelaxationTime)
		throws java.lang.Exception
	{
		super (null);

		if (null == (_exponentFunction = exponentFunction) ||
			!org.drip.numerical.common.NumberUtil.IsValid (_characteristicRelaxationTime =
				characteristicRelaxationTime) || 0. > _characteristicRelaxationTime)
		{
			throw new java.lang.Exception
				("ModifiedScaledExponentialEstimator Constructor => Invalid Inputs");
		}
	}

	/**
	 * Retrieve the Exponent Function
	 * 
	 * @return The Exponent Function
	 */

	public org.drip.function.definition.R1ToR1 exponentFunction()
	{
		return _exponentFunction;
	}

	/**
	 * Retrieve the Characteristic Relaxation Time
	 * 
	 * @return The Characteristic Relaxation Time
	 */

	public double characteristicRelaxationTime()
	{
		return _characteristicRelaxationTime;
	}

	@Override public double evaluate (
		final double t)
		throws java.lang.Exception
	{
		if (!org.drip.numerical.common.NumberUtil.IsValid (t) || 0. > t)
		{
			throw new java.lang.Exception ("ModifiedScaledExponentialEstimator::evaluate => Invalid Inputs");
		}

		return java.lang.Math.exp (
			-1. * java.lang.Math.pow (
				t / _characteristicRelaxationTime,
				_exponentFunction.evaluate (t)
			)
		);
	}
}
