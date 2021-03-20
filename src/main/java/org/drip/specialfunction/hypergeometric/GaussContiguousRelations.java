
package org.drip.specialfunction.hypergeometric;

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
 * <i>GaussContiguousRelations</i> holds the Gauss Contiguous 2F1 Relations of the Regular Hyper-geometric
 * Function. The References are:
 * 
 * <br><br>
 * 	<ul>
 * 		<li>
 * 			Gessel, I., and D. Stanton (1982): Strange Evaluations of Hyper-geometric Series <i>SIAM Journal
 * 				on Mathematical Analysis</i> <b>13 (2)</b> 295-308
 * 		</li>
 * 		<li>
 * 			Koepf, W (1995): Algorithms for m-fold Hyper-geometric Summation <i>Journal of Symbolic
 * 				Computation</i> <b>20 (4)</b> 399-417
 * 		</li>
 * 		<li>
 * 			Lavoie, J. L., F. Grondin, and A. K. Rathie (1996): Generalization of Whipple�s Theorem on the
 * 				Sum of a (_2^3)F(a,b;c;z) <i>Journal of Computational and Applied Mathematics</i> <b>72</b>
 * 				293-300
 * 		</li>
 * 		<li>
 * 			National Institute of Standards and Technology (2019): Hyper-geometric Function
 * 				https://dlmf.nist.gov/15
 * 		</li>
 * 		<li>
 * 			Wikipedia (2019): Hyper-geometric Function https://en.wikipedia.org/wiki/Hypergeometric_function
 * 		</li>
 * 	</ul>
 *
 *	<br><br>
 *  <ul>
 *		<li><b>Module </b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/ComputationalCore.md">Computational Core Module</a></li>
 *		<li><b>Library</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/FunctionAnalysisLibrary.md">Function Analysis Library</a></li>
 *		<li><b>Project</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/specialfunction/README.md">Special Function Implementation Analysis</a></li>
 *		<li><b>Package</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/specialfunction/hypergeometric/README.md">Hyper-geometric Function Estimation Schemes</a></li>
 *  </ul>
 *
 * @author Lakshmi Krishnamurthy
 */

public class GaussContiguousRelations
{
	private org.drip.specialfunction.definition.RegularHypergeometricEstimator _aPlus = null;
	private org.drip.specialfunction.definition.RegularHypergeometricEstimator _bPlus = null;
	private org.drip.specialfunction.definition.RegularHypergeometricEstimator _cPlus = null;
	private org.drip.specialfunction.definition.RegularHypergeometricEstimator _aMinus = null;
	private org.drip.specialfunction.definition.RegularHypergeometricEstimator _bMinus = null;
	private org.drip.specialfunction.definition.RegularHypergeometricEstimator _cMinus = null;
	private org.drip.specialfunction.definition.RegularHypergeometricEstimator _aPlusBPlusCPlus = null;

	/**
	 * GaussContiguousRelations Constructor
	 * 
	 * @param regularHypergeometricEstimator The Regular Hyper-geometric Estimator
	 * 
	 * @throws java.lang.Exception Thrown if the Inputs are Invalid
	 */

	public GaussContiguousRelations (
		final org.drip.specialfunction.definition.RegularHypergeometricEstimator
			regularHypergeometricEstimator)
		throws java.lang.Exception
	{
		if (null == regularHypergeometricEstimator)
		{
			throw new java.lang.Exception ("GaussContiguousRelations Constructor => Invalid Inputs");
		}

		org.drip.specialfunction.definition.HypergeometricParameters hypergeometricParameters =
			regularHypergeometricEstimator.hypergeometricParameters();

		double a = hypergeometricParameters.a();

		double b = hypergeometricParameters.b();

		double c = hypergeometricParameters.c();

		_aPlus = regularHypergeometricEstimator.albinate (
			new org.drip.specialfunction.definition.HypergeometricParameters (
				a + 1,
				b,
				c
			),
			null,
			null
		);

		_aMinus = regularHypergeometricEstimator.albinate (
			new org.drip.specialfunction.definition.HypergeometricParameters (
					a - 1,
					b,
					c
				),
				null,
				null
			);

		_bPlus = regularHypergeometricEstimator.albinate (
			new org.drip.specialfunction.definition.HypergeometricParameters (
					a,
					b + 1,
					c
				),
				null,
				null
			);

		_bMinus = regularHypergeometricEstimator.albinate (
			new org.drip.specialfunction.definition.HypergeometricParameters (
					a,
					b - 1,
					c
				),
				null,
				null
			);

		_cPlus = regularHypergeometricEstimator.albinate (
			new org.drip.specialfunction.definition.HypergeometricParameters (
					a,
					b,
					c + 1
				),
				null,
				null
			);

		_cMinus = regularHypergeometricEstimator.albinate (
			new org.drip.specialfunction.definition.HypergeometricParameters (
					a,
					b,
					c - 1
				),
				null,
				null
			);

		_aPlusBPlusCPlus = regularHypergeometricEstimator.albinate (
			new org.drip.specialfunction.definition.HypergeometricParameters (
					a + 1,
					b + 1,
					c + 1
				),
				null,
				null
			);
	}

	/**
	 * Retrieve the a+ Gauss Contiguous Function
	 * 
	 * @return The a+ Gauss Contiguous Function
	 */

	public org.drip.specialfunction.definition.RegularHypergeometricEstimator aPlus()
	{
		return _aPlus;
	}

	/**
	 * Retrieve the a- Gauss Contiguous Function
	 * 
	 * @return The a- Gauss Contiguous Function
	 */

	public org.drip.specialfunction.definition.RegularHypergeometricEstimator aMinus()
	{
		return _aMinus;
	}

	/**
	 * Retrieve the b+ Gauss Contiguous Function
	 * 
	 * @return The b+ Gauss Contiguous Function
	 */

	public org.drip.specialfunction.definition.RegularHypergeometricEstimator bPlus()
	{
		return _bPlus;
	}

	/**
	 * Retrieve the b- Gauss Contiguous Function
	 * 
	 * @return The b- Gauss Contiguous Function
	 */

	public org.drip.specialfunction.definition.RegularHypergeometricEstimator bMinus()
	{
		return _bMinus;
	}

	/**
	 * Retrieve the c+ Gauss Contiguous Function
	 * 
	 * @return The c+ Gauss Contiguous Function
	 */

	public org.drip.specialfunction.definition.RegularHypergeometricEstimator cPlus()
	{
		return _cPlus;
	}

	/**
	 * Retrieve the c- Gauss Contiguous Function
	 * 
	 * @return The c- Gauss Contiguous Function
	 */

	public org.drip.specialfunction.definition.RegularHypergeometricEstimator cMinus()
	{
		return _cMinus;
	}

	/**
	 * Retrieve the a+b+c+ Gauss Contiguous Function
	 * 
	 * @return The a+b+c+ Gauss Contiguous Function
	 */

	public org.drip.specialfunction.definition.RegularHypergeometricEstimator aPlusBPlusCPlus()
	{
		return _aPlusBPlusCPlus;
	}
}
