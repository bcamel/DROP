
package org.drip.sample.burgard2012;

import org.drip.quant.common.FormatUtil;
import org.drip.service.env.EnvManager;

/*
 * -*- mode: java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 */

/*!
 * Copyright (C) 2019 Lakshmi Krishnamurthy
 * Copyright (C) 2018 Lakshmi Krishnamurthy
 * Copyright (C) 2017 Lakshmi Krishnamurthy
 * 
 *  This file is part of DROP, an open-source library targeting risk, transaction costs, exposure, margin
 *  	calculations, valuation adjustment, and portfolio construction within and across fixed income,
 *  	credit, commodity, equity, FX, and structured products.
 *  
 *  	https://lakshmidrip.github.io/DROP/
 *  
 *  DROP is composed of three modules:
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
 * 	- Statistical Learning
 * 	- Numerical Optimizer
 * 	- Spline Builder
 * 	- Algorithm Support
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
 * <i>CounterPartyHazardLow</i> estimates the CVA Relative to V for a Call Option bought by the Bank for
 * different Close Outs and Funding Spreads using the Burgard and Kjaer (2011) Methodology for the Case where
 * the Counter Party Hazard is Low (Zero). The References are:
 *  
 * <br><br>
 *  <ul>
 *  	<li>
 *  		Burgard, C., and M. Kjaer (2014): PDE Representations of Derivatives with Bilateral Counter-party
 *  			Risk and Funding Costs <i>Journal of Credit Risk</i> <b>7 (3)</b> 1-19
 *  	</li>
 *  	<li>
 *  		Cesari, G., J. Aquilina, N. Charpillon, X. Filipovic, G. Lee, and L. Manda (2009): <i>Modeling,
 *  			Pricing, and Hedging Counter-party Credit Exposure - A Technical Guide</i> <b>Springer
 *  			Finance</b> New York
 *  	</li>
 *  	<li>
 *  		Gregory, J. (2009): Being Two-faced over Counter-party Credit Risk <i>Risk</i> <b>20 (2)</b>
 *  			86-90
 *  	</li>
 *  	<li>
 *  		Li, B., and Y. Tang (2007): <i>Quantitative Analysis, Derivatives Modeling, and Trading
 *  			Strategies in the Presence of Counter-party Credit Risk for the Fixed Income Market</i>
 *  			<b>World Scientific Publishing</b> Singapore
 *  	</li>
 *  	<li>
 *  		Piterbarg, V. (2010): Funding Beyond Discounting: Collateral Agreements and Derivatives Pricing
 *  			<i>Risk</i> <b>21 (2)</b> 97-102
 *  	</li>
 *  </ul>
 *  
 * <br><br>
 *  <ul>
 *		<li><b>Module </b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/AnalyticsCore.md">Analytics Core Module</a></li>
 *		<li><b>Library</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/XVAAnalyticsLibrary.md">XVA Analytics Library</a></li>
 *		<li><b>Project</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/sample/README.md">Sample</a></li>
 *		<li><b>Package</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/sample/burgard2012/README.md">Burgard and Kjaer (2012) Scheme</a></li>
 *  </ul>
 * <br><br>
 * 
 * @author Lakshmi Krishnamurthy
 */

public class CounterPartyHazardLow {

	private static final void CVA (
		final double dblT,
		final double dblRB,
		final double dblRC,
		final double dblLambdaB,
		final double dblLambdaC)
		throws Exception
	{
		double dblMTM_XVA___Funding_0    = -1. * (1. - Math.exp (-1. * (1. - dblRC) * dblLambdaC * dblT));

		double dblMTM_XVA___Funding_Bank = -1. * (1. - Math.exp (-1. * ((1. - dblRB) * dblLambdaB + (1. - dblRC) * dblLambdaC) * dblT));

		double dblMTM_Fair__Funding_0   = -1. * (1. - dblRC) * dblLambdaC *
			(1. - Math.exp (-1. * (dblLambdaB + dblLambdaC) * dblT)) /
			(dblLambdaB + dblLambdaC);

		double dblMTM_Fair__Funding_Bank = -1. * ((1. - dblRB) * dblLambdaB + (1. - dblRC) * dblLambdaC) *
			(1. - Math.exp (-1. * (dblLambdaB + dblLambdaC) * dblT)) /
			(dblLambdaB + dblLambdaC);

		System.out.println ("\t|| " +
			FormatUtil.FormatDouble (dblLambdaB   , 1, 1, 100.) + "% => " +
			FormatUtil.FormatDouble (dblMTM_XVA___Funding_0   , 2, 2, 100.) + "% | " +
			FormatUtil.FormatDouble (dblMTM_XVA___Funding_Bank, 2, 2, 100.) + "% | " +
			FormatUtil.FormatDouble (dblMTM_Fair__Funding_0   , 2, 2, 100.) + "% | " +
			FormatUtil.FormatDouble (dblMTM_Fair__Funding_Bank, 2, 2, 100.) + "% ||"
		);
	}

	public static final void main (
		final String[] astrArgs)
		throws Exception
	{
		EnvManager.InitEnv ("");

		double dblLambdaC = 0.00;

		double dblRB = 0.4;
		double dblRC = 0.4;
		double dblT = 5.;

		double[] adblLambdaB = new double[] {
			0.00001,
			0.005,
			0.01,
			0.015,
			0.02,
			0.025,
			0.03,
			0.035,
			0.04,
			0.045,
			0.05
		};

		System.out.println();

		System.out.println ("\t||--------------------------------------------------||");

		System.out.println ("\t||        CVA UNDER LOW COUNTER PARTY HAZARD        ||");

		System.out.println ("\t||--------------------------------------------------||");

		System.out.println ("\t|| L -> R:                                          ||");

		System.out.println ("\t||        - Close Out      => MTM XVA               ||");

		System.out.println ("\t||        - Funding Spread => None                  ||");

		System.out.println ("\t||        - Close Out      => MTM Fair Value        ||");

		System.out.println ("\t||        - Funding Spread => Bank                  ||");

		System.out.println ("\t||--------------------------------------------------||");

		for (double dblLambdaB : adblLambdaB)
			CVA (
				dblT,
				dblRB,
				dblRC,
				dblLambdaB,
				dblLambdaC
			);

		System.out.println ("\t||--------------------------------------------------||");

		System.out.println();

		EnvManager.TerminateEnv();
	}
}
