
package org.drip.execution.optimum;

/*
 * -*- mode: java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 */

/*!
 * Copyright (C) 2019 Lakshmi Krishnamurthy
 * Copyright (C) 2018 Lakshmi Krishnamurthy
 * Copyright (C) 2017 Lakshmi Krishnamurthy
 * Copyright (C) 2016 Lakshmi Krishnamurthy
 * 
 *  This file is part of DROP, an open-source library targeting risk, transaction costs, exposure, margin
 *  	calculations, and portfolio construction within and across fixed income, credit, commodity, equity,
 *  	FX, and structured products.
 *  
 *  	https://lakshmidrip.github.io/DROP/
 *  
 *  DROP is composed of three main modules:
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
 * 	- Statistical Learning Library
 * 	- Numerical Optimizer Library
 * 	- Machine Learning Library
 * 	- Spline Builder Library
 * 
 * 	Documentation for DROP is Spread Over:
 * 
 * 	- Main                     => https://lakshmidrip.github.io/DROP/
 * 	- Wiki                     => https://github.com/lakshmiDRIP/DROP/wiki
 * 	- GitHub                   => https://github.com/lakshmiDRIP/DROP
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
 * <i>TradingEnhancedDiscrete</i> contains the Trading Trajectory generated by one of the Methods outlined in
 *  the Almgren (2003) Scheme for Continuous Trading Approximation for Linear Trading Enhanced Temporary
 *  Impact Volatility. The References are:
 * 
 * <br>
 * 	<ul>
 * 		<li>
 * 			Almgren, R., and N. Chriss (1999): Value under Liquidation <i>Risk</i> <b>12 (12)</b>
 * 		</li>
 * 		<li>
 * 			Almgren, R., and N. Chriss (2000): Optimal Execution of Portfolio Transactions <i>Journal of
 * 				Risk</i> <b>3 (2)</b> 5-39
 * 		</li>
 * 		<li>
 * 			Bertsimas, D., and A. W. Lo (1998): Optimal Control of Execution Costs <i>Journal of Financial
 * 				Markets</i> <b>1</b> 1-50
 * 		</li>
 * 		<li>
 * 			Chan, L. K. C., and J. Lakonishak (1995): The Behavior of Stock Prices around Institutional
 * 				Trades <i>Journal of Finance</i> <b>50</b> 1147-1174
 * 		</li>
 * 		<li>
 * 			Keim, D. B., and A. Madhavan (1997): Transaction Costs and Investment Style: An Inter-exchange
 * 				Analysis of Institutional Equity Trades <i>Journal of Financial Economics</i> <b>46</b>
 * 				265-292
 * 		</li>
 * 	</ul>
 * <br>
 *  <ul>
 *		<li><b>Module</b>        = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/execution">Execution</a></li>
 *		<li><b>Package</b>       = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/execution/optimum">Optimum</a></li>
 *		<li><b>Specification</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/Docs/Internal/TransactionCost">Transaction Cost Analytics</a></li>
 *  </ul>
 * 
 * @author Lakshmi Krishnamurthy
 */

public class TradingEnhancedDiscrete extends org.drip.execution.optimum.EfficientTradingTrajectoryDiscrete {
	private double _dblCharacteristicSize = java.lang.Double.NaN;
	private double _dblCharacteristicTime = java.lang.Double.NaN;

	/**
	 * Construct a Standard TradingEnhancedDiscrete Instance
	 * 
	 * @param dtt The Trading Trajectory
	 * @param apep The Arithmetic Price Walk Evolution Parameters
	 * @param dblCharacteristicTime The Optimal Trajectory's "Characteristic" Time
	 * @param dblCharacteristicSize The Optimal Trajectory's "Characteristic" Size
	 * 
	 * @return The TradingEnhancedDiscrete Instance
	 */

	public static TradingEnhancedDiscrete Standard (
		final org.drip.execution.strategy.DiscreteTradingTrajectory dtt,
		final org.drip.execution.dynamics.ArithmeticPriceEvolutionParameters apep,
		final double dblCharacteristicTime,
		final double dblCharacteristicSize)
	{
		if (null == dtt || null == apep) return null;

		try {
			org.drip.measure.gaussian.R1UnivariateNormal r1un = (new
				org.drip.execution.capture.TrajectoryShortfallEstimator (dtt)).totalCostDistributionSynopsis
					(apep);

			return null == r1un ? null : new TradingEnhancedDiscrete (dtt.executionTimeNode(),
				dtt.holdings(), dtt.tradeList(), r1un.mean(), r1un.variance(), dblCharacteristicTime,
					dblCharacteristicSize, apep.temporaryExpectation().epochImpactFunction().evaluate
						(dtt.instantTradeRate()) / (apep.arithmeticPriceDynamicsSettings().epochVolatility()
							* java.lang.Math.sqrt (dtt.executionTime())));
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * TradingEnhancedDiscrete Constructor
	 * 
	 * @param adblExecutionTimeNode Array containing the Trajectory Time Nodes
	 * @param adblHoldings Array containing the Holdings
	 * @param adblTradeList Array containing the Trade List
	 * @param dblTransactionCostExpectation The Expected Transaction Cost
	 * @param dblTransactionCostVariance The Variance of the Transaction Cost
	 * @param dblCharacteristicTime The Optimal Trajectory's "Characteristic" Time
	 * @param dblCharacteristicSize The Optimal Trajectory's "Characteristic" Size
	 * @param dblMarketPower Estimate of the Relative Market Impact Power
	 * 
	 * @throws java.lang.Exception Thrown if the Inputs are Invalid
	 */

	public TradingEnhancedDiscrete (
		final double[] adblExecutionTimeNode,
		final double[] adblHoldings,
		final double[] adblTradeList,
		final double dblTransactionCostExpectation,
		final double dblTransactionCostVariance,
		final double dblCharacteristicTime,
		final double dblCharacteristicSize,
		final double dblMarketPower)
		throws java.lang.Exception
	{
		super (adblExecutionTimeNode, adblHoldings, adblTradeList, dblTransactionCostExpectation,
			dblTransactionCostVariance, dblMarketPower);

		if (!org.drip.quant.common.NumberUtil.IsValid (_dblCharacteristicTime = dblCharacteristicTime) ||
			!org.drip.quant.common.NumberUtil.IsValid (_dblCharacteristicSize = dblCharacteristicSize))
			throw new java.lang.Exception ("TradingEnhancedDiscrete Constructor => Invalid Inputs");
	}

	/**
	 * Retrieve the Optimal Trajectory Characteristic Size
	 * 
	 * @return The Optimal Trajectory Characteristic Size
	 */

	public double characteristicSize()
	{
		return _dblCharacteristicSize;
	}

	/**
	 * Retrieve the Optimal Trajectory Characteristic Time
	 * 
	 * @return The Optimal Trajectory Characteristic Time
	 */

	public double characteristicTime()
	{
		return _dblCharacteristicTime;
	}
}
