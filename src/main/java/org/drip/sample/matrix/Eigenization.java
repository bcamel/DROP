
package org.drip.sample.matrix;

import org.drip.numerical.common.*;
import org.drip.numerical.eigen.*;
import org.drip.service.env.EnvManager;

/*
 * -*- mode: java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 */

/*!
 * Copyright (C) 2019 Lakshmi Krishnamurthy
 * Copyright (C) 2018 Lakshmi Krishnamurthy
 * Copyright (C) 2017 Lakshmi Krishnamurthy
 * Copyright (C) 2016 Lakshmi Krishnamurthy
 * Copyright (C) 2015 Lakshmi Krishnamurthy
 * Copyright (C) 2014 Lakshmi Krishnamurthy
 * Copyright (C) 2013 Lakshmi Krishnamurthy
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
 * <i>Eigenization</i> demonstrates how to generate the eigenvalue and eigenvector for the Input Matrix.
 *  
 * <br><br>
 *  <ul>
 *		<li><b>Module </b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/NumericalCore.md">Numerical Core Module</a></li>
 *		<li><b>Library</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/NumericalSupportLibrary.md">Numerical Support Library</a></li>
 *		<li><b>Project</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/sample/README.md">Sample</a></li>
 *		<li><b>Package</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/sample/matrix/README.md">Linear Algebra and Matrix Utilities</a></li>
 *  </ul>
 * <br><br>
 *
 * @author Lakshmi Krishnamurthy
 */

public class Eigenization {

	private static final void EigenRun (
		final QREigenComponentExtractor qrece)
	{
		double dblCorr1 = 0.5 * Math.random();

		double dblCorr2 = 0.5 * Math.random();

		double[][] aadblA = {
			{     1.0, dblCorr1,      0.0},
			{dblCorr1,      1.0, dblCorr2},
			{     0.0, dblCorr2,      1.0}
		};

		EigenOutput eo = qrece.eigenize (aadblA);

		if (null == eo) return;

		System.out.println ("\n\t|----------------------------------------|");

		System.out.println (
			"\t|-----------" +
			FormatUtil.FormatDouble (dblCorr1, 1, 4, 1.) + " ||| " +
			FormatUtil.FormatDouble (dblCorr2, 1, 4, 1.) + " ---------|"
		);

		System.out.println ("\t|----------------------------------------|");

		for (int i = 0; i < aadblA.length; ++i) {
			java.lang.String strDump = "\t[" + FormatUtil.FormatDouble (eo.eigenValueArray()[i], 1, 4, 1.) + "] => ";

			for (int j = 0; j < aadblA.length; ++j)
				strDump += FormatUtil.FormatDouble (eo.eigenVectorArray()[i][j], 1, 4, 1.) + " | ";

			System.out.println (strDump);
		}

		EigenComponent ec = qrece.principalComponent (aadblA);

		double[] adblEigenvector = ec.eigenVector();

		java.lang.String strDump = "[" + FormatUtil.FormatDouble (ec.eigenValue(), 1, 4, 1.) + "] => ";

		for (int i = 0; i < adblEigenvector.length; ++i)
			strDump += FormatUtil.FormatDouble (adblEigenvector[i], 1, 4, 1.) + " | ";

		System.out.println ("\t" + strDump);

		System.out.println ("\t|----------------------------------------|");
	}

	public static final void main (
		final String[] astrArgs)
		throws Exception
	{
		EnvManager.InitEnv ("");

		QREigenComponentExtractor qrece = new QREigenComponentExtractor (
			50,
			0.00001
		);

		int iNumRun = 10;

		for (int iRun = 0; iRun < iNumRun; ++iRun)
			EigenRun (qrece);

		EnvManager.TerminateEnv();
	}
}
