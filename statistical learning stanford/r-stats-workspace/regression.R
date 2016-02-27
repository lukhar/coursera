library(MASS)
install.packages("ISLR")
library(ISLR)

# Simple Linear Regression
names(Boston)
?Boston

plot(medv~lstat,Boston)
fit_one = lm(medv~lstat, data=Boston)
summary(fit_one)

## draw fitted model over the plot
abline(fit_one, col="red")

names(fit_one)
confint(fit_one)

## predict results uising fitted model
predict(fit_one, data.frame(lstat=c(5, 10, 15)), interval="confidence")

# Multiple Linear Regression
fit_two = lm(medv~lstat + age, data=Boston)
summary(fit_two)

## fit everythong except for medv
fit_three = lm(medv~., data=Boston)
summary(fit_three)

par(mfrow=c(2, 2))
plot(fit_three)

## drop age and induse from model
fit_four = update(fit_three, ~ . -age -indus)
summary(fit_four)

# Nonlinear terms and interactions
fit_five = lm(medv~lstat*age, data=Boston)
summary(fit_five)

fit_six = lm(medv~lstat + I(lstat^2), data=Boston)
summary(fit_six)

## make names from data set available in script namespace
attach(Boston)

plot(mfrow=c(1, 1))
plot(medv~lstat)

## plot nonlinear function
points(lstat, fitted(fit_six), col="red", pch=20)

## fit polynomal B1x^4 + B2x^3 + B3x^2 + B4x + B5 
fit_seven = lm(medv~poly(lstat, 4))
points(lstat, fitted(fit_seven), col="blue", pch=20)

## show all available ploting characters
plot(1:20, 1:20, pch=1:20, cex=2)

# Qualitative predictors
Carseats = ISLR::Carseats
names(Carseats)
attach(Carseats)

fit_eight = lm(Sales~. +Income:Advertising +Age:Price, data=Carseats)
summary(fit_eight)

contrasts(Carseats$ShelveLoc)

# Writing R functions
regplot = function(x, y) {
  fit = lm(y~x)
  plot(x, y)
  abline(fit, col="red")
}

par(mfrow=c(1, 1))
regplot(Price, Sales)

regplot = function(x, y, ...) {
  fit = lm(y~x)
  plot(x, y, ...)
  abline(fit, col="red")
}

regplot(Price, Sales, xlab="Price", ylab="Sales", col="blue", pch=20)

