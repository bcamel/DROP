
package org.drip.spaces.tensor;

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
 * <i>GeneralizedVector</i> exposes the basic Properties of the General Vector Space. It provides the
 * 	following Functionality:
 *
 *  <ul>
 * 		<li>Retrieve the Left Edge</li>
 * 		<li>Retrieve the Right Edge</li>
 * 		<li>Retrieve the Cardinality of the Vector Space</li>
 * 		<li>Compare against the "Other" Generalized Vector Space</li>
 * 		<li>Indicate if the "Other" Generalized Vector Space is a Subset of "this"</li>
 * 		<li>Indicate if the Predictor Variate Space is bounded from the Left and the Right</li>
 * 		<li>Retrieve the "Hyper" Volume of the Vector Space</li>
 *  </ul>
 *
 *	<br>
 *  <table style="border:1px solid black;margin-left:auto;margin-right:auto;">
 *		<tr><td><b>Module </b></td> <td><a href = "https://github.com/lakshmiDRIP/DROP/tree/master/ComputationalCore.md">Computational Core Module</a></td></tr>
 *		<tr><td><b>Library</b></td> <td><a href = "https://github.com/lakshmiDRIP/DROP/tree/master/StatisticalLearningLibrary.md">Statistical Learning Library</a></td></tr>
 *		<tr><td><b>Project</b></td> <td><a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/spaces/README.md">R<sup>1</sup> and R<sup>d</sup> Vector/Tensor Spaces (Validated and/or Normed), and Function Classes</a></td></tr>
 *		<tr><td><b>Package</b></td> <td><a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/spaces/tensor/README.md">R<sup>x</sup> Continuous/Combinatorial Tensor Spaces</a></td></tr>
 *  </table>
 *	<br>
 *
 * @author Lakshmi Krishnamurthy
 */

public interface GeneralizedVector
{

	/**
	 * Retrieve the Left Edge
	 * 
	 * @return The Left Edge
	 */

	public abstract double leftEdge();

	/**
	 * Retrieve the Right Edge
	 * 
	 * @return The Right Edge
	 */

	public abstract double rightEdge();

	/**
	 * Retrieve the Cardinality of the Vector Space
	 * 
	 * @return Cardinality of the Vector Space
	 */

	public abstract Cardinality cardinality();

	/**
	 * Compare against the "Other" Generalized Vector Space
	 * 
	 * @param generalizedVectorOther The "Other" Generalized Vector Space
	 * 
	 * @return TRUE - The "Other" Generalized Vector Space matches this
	 */

	public abstract boolean match (
		final GeneralizedVector generalizedVectorOther
	);

	/**
	 * Indicate if the "Other" Generalized Vector Space is a Subset of "this"
	 * 
	 * @param generalizedVectorOther The "Other" Generalized Vector Space
	 * 
	 * @return TRUE - The "Other" Generalized Vector Space is a Subset of this
	 */

	public abstract boolean subset (
		final GeneralizedVector generalizedVectorOther
	);

	/**
	 * Indicate if the Predictor Variate Space is bounded from the Left and the Right
	 * 
	 * @return The Predictor Variate Space is bounded from the Left and the Right
	 */

	public abstract boolean isPredictorBounded();

	/**
	 * Retrieve the "Hyper" Volume of the Vector Space
	 * 
	 * @return The "Hyper" Volume of the Vector Space
	 * 
	 * @throws Exception Thrown if the Hyper Volume cannot be computed
	 */

	public abstract double hyperVolume()
		throws Exception;
}
