
package org.drip.sample.simm20estimates;

import java.util.HashMap;
import java.util.Map;

import org.drip.quant.common.FormatUtil;
import org.drip.service.env.EnvManager;
import org.drip.simm20.product.IRCurveTenorSettings;
import org.drip.simm20.product.IRNetSensitivity;

/*
 * -*- mode: java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 */

/*!
 * Copyright (C) 2018 Lakshmi Krishnamurthy
 * 
 *  This file is part of DRIP, a free-software/open-source library for buy/side financial/trading model
 *  	libraries targeting analysts and developers
 *  	https://lakshmidrip.github.io/DRIP/
 *  
 *  DRIP is composed of four main libraries:
 *  
 *  - DRIP Fixed Income - https://lakshmidrip.github.io/DRIP-Fixed-Income/
 *  - DRIP Asset Allocation - https://lakshmidrip.github.io/DRIP-Asset-Allocation/
 *  - DRIP Numerical Optimizer - https://lakshmidrip.github.io/DRIP-Numerical-Optimizer/
 *  - DRIP Statistical Learning - https://lakshmidrip.github.io/DRIP-Statistical-Learning/
 * 
 *  - DRIP Fixed Income: Library for Instrument/Trading Conventions, Treasury Futures/Options,
 *  	Funding/Forward/Overnight Curves, Multi-Curve Construction/Valuation, Collateral Valuation and XVA
 *  	Metric Generation, Calibration and Hedge Attributions, Statistical Curve Construction, Bond RV
 *  	Metrics, Stochastic Evolution and Option Pricing, Interest Rate Dynamics and Option Pricing, LMM
 *  	Extensions/Calibrations/Greeks, Algorithmic Differentiation, and Asset Backed Models and Analytics.
 * 
 *  - DRIP Asset Allocation: Library for model libraries for MPT framework, Black Litterman Strategy
 *  	Incorporator, Holdings Constraint, and Transaction Costs.
 * 
 *  - DRIP Numerical Optimizer: Library for Numerical Optimization and Spline Functionality.
 * 
 *  - DRIP Statistical Learning: Library for Statistical Evaluation and Machine Learning.
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
 * CurrencyIRNetSensitivitySolo computes the Weighted Net Sensitivities across each of the IR Risk Factors on
 *  at a time - OIS, LIBOR 1M, LIBOR 3M, LIBOR 6M LIBOR 12M, PRIME, and MUNICIPAL - within a single Currency.
 *  The References are:
 *  
 *  - Andersen, L. B. G., M. Pykhtin, and A. Sokol (2017): Credit Exposure in the Presence of Initial Margin,
 *  	https://papers.ssrn.com/sol3/papers.cfm?abstract_id=2806156, eSSRN.
 *  
 *  - Albanese, C., S. Caenazzo, and O. Frankel (2017): Regression Sensitivities for Initial Margin
 *  	Calculations, https://papers.ssrn.com/sol3/papers.cfm?abstract_id=2763488, eSSRN.
 *  
 *  - Anfuso, F., D. Aziz, P. Giltinan, and K. Loukopoulus (2017): A Sound Modeling and Back-testing
 *  	Framework for Forecasting Initial Margin Requirements,
 *  	https://papers.ssrn.com/sol3/papers.cfm?abstract_id=2716279, eSSRN.
 *  
 *  - Caspers, P., P. Giltinan, R. Lichters, and N. Nowaczyk (2017): Forecasting Initial Margin Requirements
 *  	- A Model Evaluation https://papers.ssrn.com/sol3/papers.cfm?abstract_id=2911167, eSSRN.
 *  
 *  - International Swaps and Derivatives Association (2017): SIMM v2.0 Methodology,
 *  	https://www.isda.org/a/oFiDE/isda-simm-v2.pdf.
 * 
 * @author Lakshmi Krishnamurthy
 */

public class CurrencyIRNetSensitivitySolo
{

	private static final Map<String, Double> TenorSensitivityMap (
		final double[] tenorSensitivities)
		throws Exception
	{
		Map<String, Double> oisTenorSensitivities = new HashMap<String, Double>();

		oisTenorSensitivities.put (
			"2W",
			tenorSensitivities[0]
		);

		oisTenorSensitivities.put (
			"1M",
			tenorSensitivities[1]
		);

		oisTenorSensitivities.put (
			"3M",
			tenorSensitivities[2]
		);

		oisTenorSensitivities.put (
			"6M",
			tenorSensitivities[3]
		);

		oisTenorSensitivities.put (
			"1Y",
			tenorSensitivities[4]
		);

		oisTenorSensitivities.put (
			"2Y",
			tenorSensitivities[5]
		);

		oisTenorSensitivities.put (
			"3Y",
			tenorSensitivities[6]
		);

		oisTenorSensitivities.put (
			"5Y",
			tenorSensitivities[7]
		);

		oisTenorSensitivities.put (
			"10Y",
			tenorSensitivities[8]
		);

		oisTenorSensitivities.put (
			"15Y",
			tenorSensitivities[9]
		);

		oisTenorSensitivities.put (
			"20Y",
			tenorSensitivities[10]
		);

		oisTenorSensitivities.put (
			"30Y",
			tenorSensitivities[11]
		);

		return oisTenorSensitivities;
	}

	public static final void main (
		final String[] inputs)
		throws Exception
	{
		EnvManager.InitEnv ("");

		String currency = "USD";

		IRCurveTenorSettings curveTenorSensitivitySettings = IRCurveTenorSettings.ISDA (currency);

		double[] oisTenorSensitivities = new double[]
		{
			0.017,
			0.014,
			0.012,
			0.009,
			0.067,
			0.062,
			0.093,
			0.965,
			0.004,
			0.000,
			0.000,
			0.000
		};
		double[] libor1MTenorSensitivities = new double[]
		{
			0.002,
			0.002,
			0.002,
			0.001,
			0.009,
			0.009,
			0.013,
			0.094,
			0.000,
			0.000,
			0.000,
			0.000
		};
		double[] libor3MTenorSensitivities = new double[]
		{
			0.023,
			0.017,
			0.016,
			0.012,
			0.091,
			0.088,
			0.126,
			0.941,
			0.002,
			0.000,
			0.000,
			0.000
		};
		double[] libor6MTenorSensitivities = new double[]
		{
			0.027,
			0.021,
			0.019,
			0.015,
			0.108,
			0.098,
			0.154,
			0.913,
			0.001,
			0.000,
			0.000,
			0.000
		};
		double[] libor12MTenorSensitivities = new double[]
		{
			0.001,
			0.001,
			0.001,
			0.001,
			0.005,
			0.004,
			0.007,
			0.047,
			0.000,
			0.000,
			0.000,
			0.000
		};
		double[] primeTenorSensitivities = new double[]
		{
			0.000,
			0.000,
			0.000,
			0.000,
			0.000,
			0.000,
			0.000,
			0.000,
			0.000,
			0.000,
			0.000,
			0.000
		};
		double[] municipalTenorSensitivities = new double[]
		{
			0.000,
			0.000,
			0.000,
			0.000,
			0.000,
			0.000,
			0.000,
			0.000,
			0.000,
			0.000,
			0.000,
			0.000
		};

		IRNetSensitivity irNetSensitivity = new IRNetSensitivity (
			TenorSensitivityMap (oisTenorSensitivities),
			TenorSensitivityMap (libor1MTenorSensitivities),
			TenorSensitivityMap (libor3MTenorSensitivities),
			TenorSensitivityMap (libor6MTenorSensitivities),
			TenorSensitivityMap (libor12MTenorSensitivities),
			TenorSensitivityMap (primeTenorSensitivities),
			TenorSensitivityMap (municipalTenorSensitivities),
			1.
		);

		System.out.println (
			"\t| IM Covariance[     OIS   -   OIS     ] => " +
			FormatUtil.FormatDouble (
				irNetSensitivity.covariance_OIS_OIS (curveTenorSensitivitySettings), 1, 3, 1.
			) + " ||"
		);

		System.out.println (
			"\t| IM Covariance[ LIBOR  1M - LIBOR  1M ] => " +
			FormatUtil.FormatDouble (
				irNetSensitivity.covariance_LIBOR1M_LIBOR1M (curveTenorSensitivitySettings), 1, 3, 1.
			) + " ||"
		);

		System.out.println (
			"\t| IM Covariance[ LIBOR  3M - LIBOR  3M ] => " +
			FormatUtil.FormatDouble (
				irNetSensitivity.covariance_LIBOR3M_LIBOR3M (curveTenorSensitivitySettings), 1, 3, 1.
			) + " ||"
		);

		System.out.println (
			"\t| IM Covariance[ LIBOR  6M - LIBOR  6M ] => " +
			FormatUtil.FormatDouble (
				irNetSensitivity.covariance_LIBOR6M_LIBOR6M (curveTenorSensitivitySettings), 1, 3, 1.
			) + " ||"
		);

		System.out.println (
			"\t| IM Covariance[ LIBOR 12M - LIBOR 12M ] => " +
			FormatUtil.FormatDouble (
				irNetSensitivity.covariance_LIBOR12M_LIBOR12M (curveTenorSensitivitySettings), 1, 3, 1.
			) + " ||"
		);

		System.out.println (
			"\t| IM Covariance[   PRIME   -   PRIME   ] => " +
			FormatUtil.FormatDouble (
				irNetSensitivity.covariance_PRIME_PRIME (curveTenorSensitivitySettings), 1, 3, 1.
			) + " ||"
		);

		System.out.println (
			"\t| IM Covariance[ MUNICIPAL - MUNICIPAL ] => " +
			FormatUtil.FormatDouble (
				irNetSensitivity.covariance_MUNICIPAL_MUNICIPAL (curveTenorSensitivitySettings), 1, 3, 1.
			) + " ||"
		);

		System.out.println (
			"\t| IM Covariance[     OIS   - LIBOR  1M ] => " +
			FormatUtil.FormatDouble (
				irNetSensitivity.covariance_OIS_LIBOR1M (curveTenorSensitivitySettings), 1, 3, 1.
			) + " ||"
		);

		System.out.println (
			"\t| IM Covariance[     OIS   - LIBOR  3M ] => " +
			FormatUtil.FormatDouble (
				irNetSensitivity.covariance_OIS_LIBOR3M (curveTenorSensitivitySettings), 1, 3, 1.
			) + " ||"
		);

		System.out.println (
			"\t| IM Covariance[     OIS   - LIBOR  6M ] => " +
			FormatUtil.FormatDouble (
				irNetSensitivity.covariance_OIS_LIBOR6M (curveTenorSensitivitySettings), 1, 3, 1.
			) + " ||"
		);

		System.out.println (
			"\t| IM Covariance[     OIS   - LIBOR 12M ] => " +
			FormatUtil.FormatDouble (
				irNetSensitivity.covariance_OIS_LIBOR12M (curveTenorSensitivitySettings), 1, 3, 1.
			) + " ||"
		);

		System.out.println (
			"\t| IM Covariance[     OIS   -   PRIME   ] => " +
			FormatUtil.FormatDouble (
				irNetSensitivity.covariance_OIS_PRIME (curveTenorSensitivitySettings), 1, 3, 1.
			) + " ||"
		);

		System.out.println (
			"\t| IM Covariance[     OIS   - MUNICIPAL ] => " +
			FormatUtil.FormatDouble (
				irNetSensitivity.covariance_OIS_MUNICIPAL (curveTenorSensitivitySettings), 1, 3, 1.
			) + " ||"
		);

		System.out.println (
			"\t| IM Covariance[ LIBOR  1M - LIBOR  3M ] => " +
			FormatUtil.FormatDouble (
				irNetSensitivity.covariance_LIBOR1M_LIBOR3M (curveTenorSensitivitySettings), 1, 3, 1.
			) + " ||"
		);

		System.out.println (
			"\t| IM Covariance[ LIBOR  1M - LIBOR  6M ] => " +
			FormatUtil.FormatDouble (
				irNetSensitivity.covariance_LIBOR1M_LIBOR6M (curveTenorSensitivitySettings), 1, 3, 1.
			) + " ||"
		);

		System.out.println (
			"\t| IM Covariance[ LIBOR  1M - LIBOR 12M ] => " +
			FormatUtil.FormatDouble (
				irNetSensitivity.covariance_LIBOR1M_LIBOR12M (curveTenorSensitivitySettings), 1, 3, 1.
			) + " ||"
		);

		System.out.println (
			"\t| IM Covariance[ LIBOR  1M -   PRIME   ] => " +
			FormatUtil.FormatDouble (
				irNetSensitivity.covariance_LIBOR1M_PRIME (curveTenorSensitivitySettings), 1, 3, 1.
			) + " ||"
		);

		System.out.println (
			"\t| IM Covariance[ LIBOR  1M - MUNICIPAL ] => " +
			FormatUtil.FormatDouble (
				irNetSensitivity.covariance_LIBOR1M_MUNICIPAL (curveTenorSensitivitySettings), 1, 3, 1.
			) + " ||"
		);

		System.out.println (
			"\t| IM Covariance[ LIBOR  3M - LIBOR  6M ] => " +
			FormatUtil.FormatDouble (
				irNetSensitivity.covariance_LIBOR3M_LIBOR6M (curveTenorSensitivitySettings), 1, 3, 1.
			) + " ||"
		);

		System.out.println (
			"\t| IM Covariance[ LIBOR  3M - LIBOR 12M ] => " +
			FormatUtil.FormatDouble (
				irNetSensitivity.covariance_LIBOR3M_LIBOR12M (curveTenorSensitivitySettings), 1, 3, 1.
			) + " ||"
		);

		System.out.println (
			"\t| IM Covariance[ LIBOR  3M -   PRIME   ] => " +
			FormatUtil.FormatDouble (
				irNetSensitivity.covariance_LIBOR3M_PRIME (curveTenorSensitivitySettings), 1, 3, 1.
			) + " ||"
		);

		System.out.println (
			"\t| IM Covariance[ LIBOR  3M - MUNICIPAL ] => " +
			FormatUtil.FormatDouble (
				irNetSensitivity.covariance_LIBOR3M_MUNICIPAL (curveTenorSensitivitySettings), 1, 3, 1.
			) + " ||"
		);

		System.out.println (
			"\t| IM Covariance[ LIBOR  6M - LIBOR 12M ] => " +
			FormatUtil.FormatDouble (
				irNetSensitivity.covariance_LIBOR6M_LIBOR12M (curveTenorSensitivitySettings), 1, 3, 1.
			) + " ||"
		);

		System.out.println (
			"\t| IM Covariance[ LIBOR  6M -   PRIME   ] => " +
			FormatUtil.FormatDouble (
				irNetSensitivity.covariance_LIBOR6M_PRIME (curveTenorSensitivitySettings), 1, 3, 1.
			) + " ||"
		);

		System.out.println (
			"\t| IM Covariance[ LIBOR  6M - MUNICIPAL ] => " +
			FormatUtil.FormatDouble (
				irNetSensitivity.covariance_LIBOR6M_MUNICIPAL (curveTenorSensitivitySettings), 1, 3, 1.
			) + " ||"
		);

		System.out.println (
			"\t| IM Covariance[ LIBOR 12M -   PRIME   ] => " +
			FormatUtil.FormatDouble (
				irNetSensitivity.covariance_LIBOR12M_PRIME (curveTenorSensitivitySettings), 1, 3, 1.
			) + " ||"
		);

		System.out.println (
			"\t| IM Covariance[ LIBOR 12M - MUNICIPAL ] => " +
			FormatUtil.FormatDouble (
				irNetSensitivity.covariance_LIBOR12M_MUNICIPAL (curveTenorSensitivitySettings), 1, 3, 1.
			) + " ||"
		);

		System.out.println (
			"\t| IM Covariance[   PRIME   - MUNICIPAL ] => " +
			FormatUtil.FormatDouble (
				irNetSensitivity.covariance_PRIME_MUNICIPAL (curveTenorSensitivitySettings), 1, 3, 1.
			) + " ||"
		);

		EnvManager.TerminateEnv();
	}
}
