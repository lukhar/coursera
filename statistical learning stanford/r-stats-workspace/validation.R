install.packages('ISLR')

require(ISLR)
require(boot)

?cv.glm
plot(mpg~horsepower, data = Auto)

# LOOCV
glm.fit = glm(mpg~horsepower, data = Auto)

## quite slow...
cv.glm(Auto, glm.fit)$delta

# Let's write a simple function to use formula (5, 2)
loocv = function(fit) {
  h = lm.influence(fit)$h
  mean((residuals(fit) / (1 - h)) ^ 2)
}

loocv(glm.fit)

cv.error = rep(0, 5)
degrees = 1:5

for (degree in degrees) {
  glm.fit = glm(mpg~poly(horsepower, degree), data=Auto)
  cv.error[degree] = loocv(glm.fit)
}

plot(degrees, cv.error, type="b")

# 10-fold cross validation
cv.error10 = rep(0, 5)

for (degree in degrees) {
  glm.fit = glm.fit = glm(mpg~poly(horsepower, degree), data=Auto)
  cv.error10[degree] = cv.glm(Auto, glm.fit, K = 10)$delta[1]
}

lines(degrees, cv.error10, type="b", col="red")

# Bootstrap
## Minimum risk investment - section 5.2

alpha = function(x, y) {
  vx = var(x)
  vy = var(y)
  cxy = cov(x, y)
  (vy -cxy) / (vx + vy - 2*cxy)
}

alpha(Portfolio$X, Portfolio$Y)

## What is the standard error of alpha?

alpha.fn = function(data, index) {
  with(data[index,], alpha(X, Y))
}

alpha.fn(Portfolio, 1:100)

set.seed(1)
alpha.fn(Portfolio, sample(1:100, 100, replace = TRUE))

boot.out = boot(Portfolio, alpha.fn, R = 1000)
boot.out
plot(boot.out)
