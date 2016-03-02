require(ISLR)

load("5.R.RData", verbose = TRUE)

# question 1
glm.fit = glm(y~X1+X2, data=Xy)
summary(glm.fit)

# question 2
head(Xy)
?matplot
matplot(Xy,type="l")
legend("topright",legend=names(Xy),lty=1:3,col=1:3)

# answer: our estimate is too low! Why? 
# There is very strong autocorrelation 
# between consecutive rows of the data matrix.
# Roughly speaking, we have about 10-20 repeats 
# of every data point, so the sample size is 
# in effect much smaller than the number 
# of rows (1000 in this case).


# question 3
boot.fn = function(data, index) {
  return (coef(lm(y~X1+X2, data = data, subset = index))) 
}
boot(Xy, boot.fn, R = 1000)

?tsboot

# quenstion 4
boot.fnts = function(data) {
  return (coef(lm(y~X1+X2, data = data))) 
}
tsboot(Xy, boot.fnts, R=1000, l = 100, sim = "fixed")