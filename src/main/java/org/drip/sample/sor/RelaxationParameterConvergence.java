
package org.drip.sample.sor;

import org.drip.numerical.iterativesolver.SuccessiveOverRelaxationConvergenceAnalyzer;
import org.drip.service.common.FormatUtil;
import org.drip.service.env.EnvManager;

/*
 * -*- mode: java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 */

/*!
 * Copyright (C) 2025 Lakshmi Krishnamurthy
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
 * <i>RelaxationParameterConvergence</i> illustrates the Convergence Rate Estimation of the Relaxation
 * 	Parameter. The References are:
 * 
 * <br><br>
 * 	<ul>
 * 		<li>
 * 			Greenbaum, A. (1997): <i>Iterative Methods for Solving Linear Systems</i> <b>Society for
 * 				Industrial and Applied Mathematics</b> Philadelphia, PA
 * 		</li>
 * 		<li>
 * 			Hackbusch, W. (2016): <i>Iterative Solution of Large Sparse Systems of Equations</i>
 * 				<b>Spring-Verlag</b> Berlin, Germany
 * 		</li>
 * 		<li>
 * 			Wikipedia (2023): Symmetric Successive Over-Relaxation
 * 				https://en.wikipedia.org/wiki/Symmetric_successive_over-relaxation
 * 		</li>
 * 		<li>
 * 			Wikipedia (2024): Successive Over-Relaxation
 * 				https://en.wikipedia.org/wiki/Successive_over-relaxation
 * 		</li>
 * 		<li>
 * 			Young, D. M. (1950): <i>Iterative methods for solving partial difference equations of elliptical
 * 				type</i> <b>Harvard University</b> Cambridge, MA
 * 		</li>
 * 	</ul>
 * 
 * <br><br>
 *  <ul>
 *		<li><b>Module </b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/ComputationalCore.md">Computational Core Module</a></li>
 *		<li><b>Library</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/NumericalAnalysisLibrary.md">Numerical Analysis Library</a></li>
 *		<li><b>Project</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/sample/README.md">DROP API Construction and Usage</a></li>
 *		<li><b>Package</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/sample/sor/README.md">Successive Over-relaxation Customization/Usage</a></li>
 *  </ul>
 * <br><br>
 *
 * @author Lakshmi Krishnamurthy
 */

public class RelaxationParameterConvergence
{

	/**
	 * Entry Point
	 * 
	 * @param argumentArray Command Line Argument Array
	 * 
	 * @throws Exception Thrown on Error/Exception Situation
	 */

	public static final void main (
		final String[] argumentArray)
		throws Exception
	{
		EnvManager.InitEnv (
			""
		);

		double[][] squareMatrix = new double[][] {
			{ 4., -1., -6.,  0.},
			{-5., -4., 10.,  8.},
			{ 0.,  9.,  4., -2.},
			{ 1.,  0., -7.,  5.},
		};

		double[] relaxationParameterArray = new double[] {
			0.20,
			0.40,
			0.60,
			0.80,
			1.00,
			1.20,
			1.40,
			1.60,
			1.80
		};

		double[] jacobiIterationMatrixSpectralRadiusArray = new double[] {
			0.60,
			0.80,
			0.95,
		};

		System.out.println ("\t|---------------------------------------------------------||");

		System.out.println ("\t|        RELAXATION PARAMETER CONVERGENCE ANALYSIS        ||");

		System.out.println ("\t|---------------------------------------------------------||");

		System.out.println ("\t|  Input L -> R:                                          ||");

		System.out.println ("\t|    - Relaxation Parameter                               ||");

		System.out.println ("\t|    - Jacobi Iteration Matrix Spectral Radius            ||");

		System.out.println ("\t|---------------------------------------------------------||");

		System.out.println ("\t|  Output L -> R:                                         ||");

		System.out.println ("\t|    - Relaxation Parameter Convergence Rate              ||");

		System.out.println ("\t|    - Relaxation Parameter GS Convergence Rate           ||");

		System.out.println ("\t|    - Optimal Relaxation Parameter                       ||");

		System.out.println ("\t|    - Optimal Convergence Rate                           ||");

		System.out.println ("\t|---------------------------------------------------------||");

		for (double relaxationParameter : relaxationParameterArray) {
			for (double jacobiIterationMatrixSpectralRadius : jacobiIterationMatrixSpectralRadiusArray) {
				SuccessiveOverRelaxationConvergenceAnalyzer successiveOverRelaxationConvergenceAnalyzer =
					new SuccessiveOverRelaxationConvergenceAnalyzer (
						squareMatrix,
						relaxationParameter,
						jacobiIterationMatrixSpectralRadius
					);

				System.out.println (
					"\t| [" + FormatUtil.FormatDouble (relaxationParameter, 1, 2, 1.) + " |" +
						FormatUtil.FormatDouble (jacobiIterationMatrixSpectralRadius, 1, 2, 1.) + "] => " +
						FormatUtil.FormatDouble (
							successiveOverRelaxationConvergenceAnalyzer.rate(),
							1,
							4,
							1.
						) + " | " + FormatUtil.FormatDouble (
							successiveOverRelaxationConvergenceAnalyzer.gaussSeidelRate(),
							1,
							4,
							1.
						) + " | " + FormatUtil.FormatDouble (
							successiveOverRelaxationConvergenceAnalyzer.optimalRelaxationParameter(),
							1,
							4,
							1.
						) + " | " + FormatUtil.FormatDouble (
							successiveOverRelaxationConvergenceAnalyzer.optimalRate(),
							1,
							4,
							1.
						) + " ||"
				);
			}
		}

		System.out.println ("\t|---------------------------------------------------------||");

		EnvManager.TerminateEnv();
	}
}
