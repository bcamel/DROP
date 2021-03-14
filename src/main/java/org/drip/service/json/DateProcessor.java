
package org.drip.service.json;

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
 * <i>DateProcessor</i> Sets Up and Executes a JSON Based In/Out Date Related Service.
 * 
 * <br><br>
 *  <ul>
 *		<li><b>Module </b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/ComputationalCore.md">Computational Core Module</a></li>
 *		<li><b>Library</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/ComputationSupportLibrary.md">Computation Support</a></li>
 *		<li><b>Project</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/service/README.md">Environment, Product/Definition Containers, and Scenario/State Manipulation APIs</a></li>
 *		<li><b>Package</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/service/json/README.md">JSON Based Valuation Request Service</a></li>
 *  </ul>
 * <br><br>
 *
 * @author Lakshmi Krishnamurthy
 */

public class DateProcessor {

	/**
	 * JSON Based in/out Date Holiday Check Thunker
	 * 
	 * @param jsonParameter JSON Date Request Parameters
	 * 
	 * @return JSON Date Holiday Check Response
	 */

	@SuppressWarnings ("unchecked") static final org.drip.service.representation.JSONObject IsHoliday (
		final org.drip.service.representation.JSONObject jsonParameter)
	{
		org.drip.analytics.date.JulianDate dt = org.drip.service.jsonparser.Converter.DateEntry (jsonParameter,
			"Date");

		if (null == dt) return null;

		boolean bIsHoliday = false;

		try {
			bIsHoliday = org.drip.analytics.daycount.Convention.IsHoliday (dt.julian(),
				org.drip.service.jsonparser.Converter.StringEntry (jsonParameter, "Calendar"));
		} catch (java.lang.Exception e) {
			e.printStackTrace();

			return null;
		}

		org.drip.service.representation.JSONObject jsonResponse = new org.drip.service.representation.JSONObject();

		jsonResponse.put ("IsHoliday", bIsHoliday);

		return jsonResponse;
	}

	/**
	 * JSON Based in/out Date Adjustment Thunker
	 * 
	 * @param jsonParameter JSON Date Request Parameters
	 * 
	 * @return JSON Date Adjustment Response
	 */

	@SuppressWarnings ("unchecked") static final org.drip.service.representation.JSONObject AdjustBusinessDays (
		final org.drip.service.representation.JSONObject jsonParameter)
	{
		org.drip.analytics.date.JulianDate dt = org.drip.service.jsonparser.Converter.DateEntry (jsonParameter,
			"Date");

		if (null == dt) return null;

		java.lang.String strCalendar = org.drip.service.jsonparser.Converter.StringEntry (jsonParameter,
			"Calendar");

		int iDaysToAdjust = 0;

		try {
			iDaysToAdjust = org.drip.service.jsonparser.Converter.IntegerEntry (jsonParameter, "DaysToAdjust");
		} catch (java.lang.Exception e) {
			e.printStackTrace();

			return null;
		}

		org.drip.analytics.date.JulianDate dtOut = dt.addBusDays (iDaysToAdjust, strCalendar);

		if (null == dtOut) return null;

		org.drip.service.representation.JSONObject jsonResponse = new org.drip.service.representation.JSONObject();

		jsonResponse.put ("DateOut", dtOut.toString());

		return jsonResponse;
	}

	/**
	 * JSON Based in/out Date Offset Thunker
	 * 
	 * @param jsonParameter JSON Date Request Parameters
	 * 
	 * @return JSON Date Offset Response
	 */

	@SuppressWarnings ("unchecked") static final org.drip.service.representation.JSONObject AddDays (
		final org.drip.service.representation.JSONObject jsonParameter)
	{
		org.drip.analytics.date.JulianDate dt = org.drip.service.jsonparser.Converter.DateEntry (jsonParameter,
			"Date");

		if (null == dt) return null;

		int iDaysToAdd = 0;

		try {
			iDaysToAdd = org.drip.service.jsonparser.Converter.IntegerEntry (jsonParameter, "DaysToAdd");
		} catch (java.lang.Exception e) {
			e.printStackTrace();

			return null;
		}

		org.drip.analytics.date.JulianDate dtOut = dt.addDays (iDaysToAdd);

		if (null == dtOut) return null;

		org.drip.service.representation.JSONObject jsonResponse = new org.drip.service.representation.JSONObject();

		jsonResponse.put ("DateOut", dtOut.toString());

		return jsonResponse;
	}
}
