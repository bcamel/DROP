# DROP

*v5.99*  *02 January 2024*

<p align="center"><img src="https://github.com/lakshmiDRIP/DROP/blob/master/DRIP_Logo.gif?raw=true" width="100"></p>

DROP implements libraries targeting analytics/risk, transaction cost analytics, asset liability analytics, capital, exposure, and margin analytics, valuation adjustment analytics, and portfolio construction analytics within and across fixed income, credit, commodity, equity, FX, and structured products. It also includes auxiliary libraries for graph algorithms, numerical analysis, numerical optimization, spline builder, model validation, statistical learning, and computational support

DROP is composed of three modules.

 * [***Product     Core Module***](https://github.com/lakshmiDRIP/DROP/blob/master/ProductCore.md)         =>   Fixed Income Product Analytics, Loan Analytics, and Transaction Cost Analytics.
 * [***Portfolio   Core Module***](https://github.com/lakshmiDRIP/DROP/blob/master/PortfolioCore.md)       =>   Portfolio Contruction and Asset Liability, along with Exposure, Margin, XVA, and Capital Analytics.
 * [***Computation Core Module***](https://github.com/lakshmiDRIP/DROP/blob/master/ComputationalCore.md)   =>   Algorithm/Computation Support, Function Analysis, Model Validation, Numerical Analysis, Numerical Optimizer, Spline Builder, Graph Algorithms, and Statistical Learning.


## Pointers

[![Travis](https://travis-ci.org/lakshmiDRIP/DROP.svg)](https://travis-ci.org/lakshmiDRIP/DROP)    [![CircleCI](https://img.shields.io/circleci/project/github/lakshmiDRIP/DROP.svg)](https://circleci.com/gh/lakshmiDRIP/workflows/DROP)    [![CircleCI](https://circleci.com/gh/lakshmiDRIP/DROP.svg?style=svg)](https://circleci.com/gh/lakshmiDRIP/DROP)    [![Build status](https://ci.appveyor.com/api/projects/status/m5p8sfeth4cewr4v?svg=true)](https://ci.appveyor.com/project/lakshmiDRIP/drop)    
[![Git](https://img.shields.io/github/release/lakshmiDRIP/DROP.svg)](https://github.com/lakshmiDRIP/DROP/releases)    
[![Stack Overflow](http://img.shields.io/:stack%20overflow-drip-brightgreen.svg)](http://stackoverflow.com/questions/tagged/drip)    [![Git](http://dmlc.github.io/img/apache2.svg)](./LICENSE)    
[![Join the chat at https://gitter.im/lakshmiDRIPDROP](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/lakshmiDRIPDROP?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)    

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/7270e4b57c50483699448bf32721ab10)](https://www.codacy.com/app/lakshmiDRIP/DROP?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=DROP/DROP&amp;utm_campaign=Badge_Grade)   [![Codacy Badge](https://api.codacy.com/project/badge/Coverage/7270e4b57c50483699448bf32721ab10)](https://www.codacy.com/app/lakshmiDRIP/DROP?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=DROP/DROP&amp;utm_campaign=Badge_Coverage)   [![codecov.io](http://codecov.io/github/lakshmiDRIP/DROP/coverage.svg?branch=master)](https://codecov.io/gh/lakshmiDRIP/DROP/branch/master)   [![Coverage Status](https://coveralls.io/repos/github/lakshmiDRIP/DROP/badge.svg?branch=master)](https://coveralls.io/github/lakshmiDRIP/DROP?branch=master)   [![Coverity Status](https://scan.coverity.com/projects/14574/badge.svg)](https://scan.coverity.com/projects/lakshmidrip-drop)    
[![Documentation Status](https://readthedocs.org/projects/dripdrop/badge/?version=latest)](http://dripdrop.readthedocs.io/en/latest/?badge=latest)  [![Javadoc](https://readthedocs.org/projects/xgboost/badge/?version=latest)](https://lakshmidrip.github.io/DROP/Javadoc/index.html)  [![Other](https://readthedocs.org/projects/xgboost/badge/?version=latest)](https://github.com/lakshmiDRIP/DROP/tree/master/Docs)

[![Average time to resolve an issue](http://isitmaintained.com/badge/resolution/lakshmiDRIP/DROP.svg)](http://isitmaintained.com/project/lakshmiDRIP/DROP "Average time to resolve an issue")   [![Percentage of issues still open](http://isitmaintained.com/badge/open/lakshmiDRIP/DROP.svg)](http://isitmaintained.com/project/lakshmiDRIP/DROP "Percentage of issues still open")

[![Awesome](https://cdn.rawgit.com/sindresorhus/awesome/d7305f38d29fed78fa85652e3a63e154dd8e8829/media/badge.svg)](https://github.com/sindresorhus/awesome)

## Repo Structure

 [**Module, Library, and Project Layout**](https://github.com/lakshmiDRIP/DROP/blob/master/Taxonomy.md)

  |        Project         |  Home  |  Issues  |  Library  |  Module  |
  |------------------------|--------|----------|-----------|----------|
  | alm                    | [README](https://github.com/lakshmiDRIP/DROP/blob/master/src/main/java/org/drip/alm/README.md) | [Git](https://github.com/lakshmiDRIP/DROP/issues?q=is%3Aopen+is%3Aissue+label%3Aalm) | [Asset Liability Analytics](https://github.com/lakshmiDRIP/DROP/blob/master/AssetLiabilityAnalyticsLibrary.md) | [Portfolio](https://github.com/lakshmiDRIP/DROP/blob/master/PortfolioCore.md) |
  | analytics              | [README](https://github.com/lakshmiDRIP/DROP/blob/master/src/main/java/org/drip/analytics/README.md) | [Git](https://github.com/lakshmiDRIP/DROP/issues?q=is%3Aopen+is%3Aissue+label%3Aanalytics) | [Fixed Income Analytics](https://github.com/lakshmiDRIP/DROP/blob/master/FixedIncomeAnalyticsLibrary.md) | [Product](https://github.com/lakshmiDRIP/DROP/blob/master/ProductCore.md) |
  | capital                | [README](https://github.com/lakshmiDRIP/DROP/blob/master/src/main/java/org/drip/capital/README.md) | [Git](https://github.com/lakshmiDRIP/DROP/issues?q=is%3Aopen+is%3Aissue+label%3Acapital) | [Capital Analytics](https://github.com/lakshmiDRIP/DROP/blob/master/CapitalAnalyticsLibrary.md) | [Portfolio](https://github.com/lakshmiDRIP/DROP/blob/master/PortfolioCore.md) |
  | dynamics               | [README](https://github.com/lakshmiDRIP/DROP/blob/master/src/main/java/org/drip/dynamics/README.md) | [Git](https://github.com/lakshmiDRIP/DROP/issues?q=is%3Aopen+is%3Aissue+label%3Adynamics) | [Fixed Income Analytics](https://github.com/lakshmiDRIP/DROP/blob/master/FixedIncomeAnalyticsLibrary.md) | [Product](https://github.com/lakshmiDRIP/DROP/blob/master/ProductCore.md) |
  | execution              | [README](https://github.com/lakshmiDRIP/DROP/blob/master/src/main/java/org/drip/execution/README.md) | [Git](https://github.com/lakshmiDRIP/DROP/issues?q=is%3Aopen+is%3Aissue+label%3Aexecution) | [Transaction Cost Analytics](https://github.com/lakshmiDRIP/DROP/blob/master/TransactionCostAnalyticsLibrary.md) | [Product](https://github.com/lakshmiDRIP/DROP/blob/master/ProductCore.md) |
  | exposure               | [README](https://github.com/lakshmiDRIP/DROP/blob/master/src/main/java/org/drip/exposure/README.md) | [Git](https://github.com/lakshmiDRIP/DROP/issues?q=is%3Aopen+is%3Aissue+label%3Aexposure) | [Exposure Analytics](https://github.com/lakshmiDRIP/DROP/blob/master/ExposureAnalyticsLibrary.md) | [Portfolio](https://github.com/lakshmiDRIP/DROP/blob/master/PortfolioCore.md) |
  | feed                   | [README](https://github.com/lakshmiDRIP/DROP/blob/master/src/main/java/org/drip/feed/README.md) | [Git](https://github.com/lakshmiDRIP/DROP/issues?q=is%3Aopen+is%3Aissue+label%3Afeed) | [Computation Support](https://github.com/lakshmiDRIP/DROP/blob/master/ComputationSupportLibrary.md) | [Computational](https://github.com/lakshmiDRIP/DROP/blob/master/ComputationalCore.md) |
  | function               | [README](https://github.com/lakshmiDRIP/DROP/blob/master/src/main/java/org/drip/function/README.md) | [Git](https://github.com/lakshmiDRIP/DROP/issues?q=is%3Aopen+is%3Aissue+label%3Afunction) | [Numerical Analysis](https://github.com/lakshmiDRIP/DROP/blob/master/NumericalAnalysisLibrary.md) | [Computational](https://github.com/lakshmiDRIP/DROP/blob/master/ComputationalCore.md) |
  | graph                  | [README](https://github.com/lakshmiDRIP/DROP/blob/master/src/main/java/org/drip/graph/README.md) | [Git](https://github.com/lakshmiDRIP/DROP/issues?q=is%3Aopen+is%3Aissue+label%3Afunction) | [Numerical Analysis](https://github.com/lakshmiDRIP/DROP/blob/master/GraphAlgorithmLibrary.md) | [Computational](https://github.com/lakshmiDRIP/DROP/blob/master/ComputationalCore.md) |
  | historical             | [README](https://github.com/lakshmiDRIP/DROP/blob/master/src/main/java/org/drip/historical/README.md) | [Git](https://github.com/lakshmiDRIP/DROP/issues?q=is%3Aopen+is%3Aissue+label%3Ahistorical) | [Computation Support](https://github.com/lakshmiDRIP/DROP/blob/master/ComputationSupportLibrary.md) | [Computational](https://github.com/lakshmiDRIP/DROP/blob/master/ComputationalCore.md) |
  | json                   | [README](https://github.com/lakshmiDRIP/DROP/blob/master/src/main/java/org/drip/json/README.md) | [Git](https://github.com/lakshmiDRIP/DROP/issues?q=is%3Aopen+is%3Aissue+label%3Ajson) | [Computation Support](https://github.com/lakshmiDRIP/DROP/blob/master/ComputationSupportLibrary.md) | [Computational](https://github.com/lakshmiDRIP/DROP/blob/master/ComputationalCore.md) |
  | learning               | [README](https://github.com/lakshmiDRIP/DROP/blob/master/src/main/java/org/drip/learning/README.md) | [Git](https://github.com/lakshmiDRIP/DROP/issues?q=is%3Aopen+is%3Aissue+label%3Alearning) | [Statistical Learning](https://github.com/lakshmiDRIP/DROP/blob/master/StatisticalLearningLibrary.md) | [Computational](https://github.com/lakshmiDRIP/DROP/blob/master/ComputationalCore.md) |
  | loan                   | [README](https://github.com/lakshmiDRIP/DROP/blob/master/src/main/java/org/drip/loan/README.md) | [Git](https://github.com/lakshmiDRIP/DROP/issues?q=is%3Aopen+is%3Aissue+label%3Aloan) | [Loan Analytics](https://github.com/lakshmiDRIP/DROP/blob/master/LoanAnalyticsLibrary.md) | [Product](https://github.com/lakshmiDRIP/DROP/blob/master/ProductCore.md) |
  | market                 | [README](https://github.com/lakshmiDRIP/DROP/blob/master/src/main/java/org/drip/market/README.md) | [Git](https://github.com/lakshmiDRIP/DROP/issues?q=is%3Aopen+is%3Aissue+label%3Amarket) | [Fixed Income Analytics](https://github.com/lakshmiDRIP/DROP/blob/master/FixedIncomeAnalyticsLibrary.md) | [Product](https://github.com/lakshmiDRIP/DROP/blob/master/ProductCore.md) |
  | measure                | [README](https://github.com/lakshmiDRIP/DROP/blob/master/src/main/java/org/drip/measure/README.md) | [Git](https://github.com/lakshmiDRIP/DROP/issues?q=is%3Aopen+is%3Aissue+label%3Ameasure) | [Numerical Analysis](https://github.com/lakshmiDRIP/DROP/blob/master/NumericalAnalysisLibrary.md) | [Computational](https://github.com/lakshmiDRIP/DROP/blob/master/ComputationalCore.md) |
  | numerical              | [README](https://github.com/lakshmiDRIP/DROP/blob/master/src/main/java/org/drip/numerical/README.md) | [Git](https://github.com/lakshmiDRIP/DROP/issues?q=is%3Aopen+is%3Aissue+label%3Anumerical) | [Numerical Analysis](https://github.com/lakshmiDRIP/DROP/blob/master/NumericalAnalysisLibrary.md) | [Computational](https://github.com/lakshmiDRIP/DROP/blob/master/ComputationalCore.md) |
  | optimization           | [README](https://github.com/lakshmiDRIP/DROP/blob/master/src/main/java/org/drip/optimization/README.md) | [Git](https://github.com/lakshmiDRIP/DROP/issues?q=is%3Aopen+is%3Aissue+label%3Aoptimization) | [Numerical Optimizer](https://github.com/lakshmiDRIP/DROP/blob/master/NumericalOptimizerLibrary.md) | [Computational](https://github.com/lakshmiDRIP/DROP/blob/master/ComputationalCore.md) |
  | param                  | [README](https://github.com/lakshmiDRIP/DROP/blob/master/src/main/java/org/drip/param/README.md) | [Git](https://github.com/lakshmiDRIP/DROP/issues?q=is%3Aopen+is%3Aissue+label%3Aparam) | [Fixed Income Analytics](https://github.com/lakshmiDRIP/DROP/blob/master/FixedIncomeAnalyticsLibrary.md) | [Product](https://github.com/lakshmiDRIP/DROP/blob/master/ProductCore.md) |
  | portfolio construction | [README](https://github.com/lakshmiDRIP/DROP/blob/master/src/main/java/org/drip/portfolioconstruction/README.md) | [Git](https://github.com/lakshmiDRIP/DROP/issues?q=is%3Aopen+is%3Aissue+label%3Aportfolioconstruction) | [Asset Allocation Analytics](https://github.com/lakshmiDRIP/DROP/blob/master/AssetAllocationAnalyticsLibrary.md) | [Portfolio](https://github.com/lakshmiDRIP/DROP/blob/master/PortfolioCore.md) |
  | pricer                 | [README](https://github.com/lakshmiDRIP/DROP/blob/master/src/main/java/org/drip/pricer/README.md) | [Git](https://github.com/lakshmiDRIP/DROP/issues?q=is%3Aopen+is%3Aissue+label%3Apricer) | [Fixed Income Analytics](https://github.com/lakshmiDRIP/DROP/blob/master/FixedIncomeAnalyticsLibrary.md) | [Product](https://github.com/lakshmiDRIP/DROP/blob/master/ProductCore.md) |
  | product                | [README](https://github.com/lakshmiDRIP/DROP/blob/master/src/main/java/org/drip/product/README.md) | [Git](https://github.com/lakshmiDRIP/DROP/issues?q=is%3Aopen+is%3Aissue+label%3Aproduct) | [Fixed Income Analytics](https://github.com/lakshmiDRIP/DROP/blob/master/FixedIncomeAnalyticsLibrary.md) | [Product](https://github.com/lakshmiDRIP/DROP/blob/master/ProductCore.md) |
  | regression             | [README](https://github.com/lakshmiDRIP/DROP/blob/master/src/main/java/org/drip/regression/README.md) | [Git](https://github.com/lakshmiDRIP/DROP/issues?q=is%3Aopen+is%3Aissue+label%3Aregression) | [Computation Support](https://github.com/lakshmiDRIP/DROP/blob/master/ComputationSupportLibrary.md) | [Computational](https://github.com/lakshmiDRIP/DROP/blob/master/ComputationalCore.md) |
  | sequence               | [README](https://github.com/lakshmiDRIP/DROP/blob/master/src/main/java/org/drip/sequence/README.md) | [Git](https://github.com/lakshmiDRIP/DROP/issues?q=is%3Aopen+is%3Aissue+label%3Asequence) | [Statistical Learning](https://github.com/lakshmiDRIP/DROP/blob/master/StatisticalLearningLibrary.md) | [Computational](https://github.com/lakshmiDRIP/DROP/blob/master/ComputationalCore.md) |
  | service                | [README](https://github.com/lakshmiDRIP/DROP/blob/master/src/main/java/org/drip/service/README.md) | [Git](https://github.com/lakshmiDRIP/DROP/issues?q=is%3Aopen+is%3Aissue+label%3Aservice) | [Computation Support](https://github.com/lakshmiDRIP/DROP/blob/master/ComputationSupportLibrary.md) | [Computational](https://github.com/lakshmiDRIP/DROP/blob/master/ComputationalCore.md) |
  | simm                   | [README](https://github.com/lakshmiDRIP/DROP/blob/master/src/main/java/org/drip/simm/README.md) | [Git](https://github.com/lakshmiDRIP/DROP/issues?q=is%3Aopen+is%3Aissue+label%3Asimm) | [Margin Analytics](https://github.com/lakshmiDRIP/DROP/blob/master/MarginAnalyticsLibrary.md) | [Portfolio](https://github.com/lakshmiDRIP/DROP/blob/master/PortfolioCore.md) |
  | spaces                 | [README](https://github.com/lakshmiDRIP/DROP/blob/master/src/main/java/org/drip/spaces/README.md) | [Git](https://github.com/lakshmiDRIP/DROP/issues?q=is%3Aopen+is%3Aissue+label%3Aspaces) | [Statistical Learning](https://github.com/lakshmiDRIP/DROP/blob/master/StatisticalLearningLibrary.md) | [Computational](https://github.com/lakshmiDRIP/DROP/blob/master/ComputationalCore.md) |
  | special function       | [README](https://github.com/lakshmiDRIP/DROP/blob/master/src/main/java/org/drip/specialfunction/README.md) | [Git](https://github.com/lakshmiDRIP/DROP/issues?q=is%3Aopen+is%3Aissue+label%3Aspecialfunction) | [Function Analysis](https://github.com/lakshmiDRIP/DROP/blob/master/FunctionAnalysisLibrary.md) | [Computational](https://github.com/lakshmiDRIP/DROP/blob/master/ComputationalCore.md) |
  | spline                 | [README](https://github.com/lakshmiDRIP/DROP/blob/master/src/main/java/org/drip/spline/README.md) | [Git](https://github.com/lakshmiDRIP/DROP/issues?q=is%3Aopen+is%3Aissue+label%3Aspline) | [Spline Builder](https://github.com/lakshmiDRIP/DROP/blob/master/SplineBuilderLibrary.md) | [Computational](https://github.com/lakshmiDRIP/DROP/blob/master/ComputationalCore.md) |
  | state                  | [README](https://github.com/lakshmiDRIP/DROP/blob/master/src/main/java/org/drip/state/README.md) | [Git](https://github.com/lakshmiDRIP/DROP/issues?q=is%3Aopen+is%3Aissue+label%3Astate) | [Fixed Income Analytics](https://github.com/lakshmiDRIP/DROP/blob/master/FixedIncomeAnalyticsLibrary.md) | [Product](https://github.com/lakshmiDRIP/DROP/blob/master/ProductCore.md) |
  | template               | [README](https://github.com/lakshmiDRIP/DROP/blob/master/src/main/java/org/drip/template/README.md) | [Git](https://github.com/lakshmiDRIP/DROP/issues?q=is%3Aopen+is%3Aissue+label%3Atemplate) | [Fixed Income Analytics](https://github.com/lakshmiDRIP/DROP/blob/master/FixedIncomeAnalyticsLibrary.md) | [Product](https://github.com/lakshmiDRIP/DROP/blob/master/ProductCore.md) |
  | validation             | [README](https://github.com/lakshmiDRIP/DROP/blob/master/src/main/java/org/drip/validation/README.md) | [Git](https://github.com/lakshmiDRIP/DROP/issues?q=is%3Aopen+is%3Aissue+label%3Avalidation) | [Model Validation](https://github.com/lakshmiDRIP/DROP/blob/master/ModelValidationAnalyticsLibrary.md) | [Computational](https://github.com/lakshmiDRIP/DROP/blob/master/ComputationalCore.md) |
  | xva                    | [README](https://github.com/lakshmiDRIP/DROP/blob/master/src/main/java/org/drip/xva/README.md) | [Git](https://github.com/lakshmiDRIP/DROP/issues?q=is%3Aopen+is%3Aissue+label%3Axva) | [XVA Analytics](https://github.com/lakshmiDRIP/DROP/blob/master/XVAAnalyticsLibrary.md) | [Portfolio](https://github.com/lakshmiDRIP/DROP/blob/master/PortfolioCore.md) |


## Installation

 Installation is as simple as building a jar and dropping into the classpath. There are no other dependencies.

## Samples

  [**Java Samples**](https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/sample)   |   [**Excel Samples**](https://github.com/lakshmiDRIP/DROP/tree/master/Excel)   |   [**Test Data**](https://github.com/lakshmiDRIP/DROP/tree/master/Daemons)

## Documentation

 [**Javadoc API**](https://lakshmidrip.github.io/DROP/Javadoc/index.html) | [**DROP Specifications**](https://github.com/lakshmiDRIP/DROP/tree/master/Docs/Internal) | [**Release Notes**](https://github.com/lakshmiDRIP/DROP/tree/master/ReleaseNotes) | User guide is a work in progress!

## Misc

  [**JUnit Tests**](https://lakshmidrip.github.io/DROP/junit/index.html)   |   [**Jacoco Coverage**](https://lakshmidrip.github.io/DROP/jacoco/index.html)   |   [**Jacoco Session**](https://lakshmidrip.github.io/DROP/jacoco/jacoco-sessions.html)   |   [**Credit Attributions**](https://lakshmidrip.github.io/DROP/credits.html)   |   [**Version Specifications**](https://lakshmidrip.github.io/DROP/version.html)

## Contact

lakshmidrip7977@gmail.com

[![codecov.io](https://codecov.io/gh/lakshmiDRIP/DROP/branch/master/graphs/sunburst.svg)](https://codecov.io/gh/lakshmiDRIP/DROP/branch/master)  [![codecov.io](https://codecov.io/gh/lakshmiDRIP/DROP/branch/master/graphs/icicle.svg)](https://codecov.io/gh/lakshmiDRIP/DROP/branch/master)  [![codecov.io](https://codecov.io/gh/lakshmiDRIP/DROP/branch/master/graphs/tree.svg)](https://codecov.io/gh/lakshmiDRIP/DROP/branch/master)  [![codecov.io](https://codecov.io/gh/lakshmiDRIP/DROP/branch/master/graphs/commits.svg)](https://codecov.io/gh/lakshmiDRIP/DROP/branch/master)  
