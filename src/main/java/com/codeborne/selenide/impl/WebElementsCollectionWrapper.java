package com.codeborne.selenide.impl;

import com.codeborne.selenide.Driver;
import org.openqa.selenium.WebElement;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ParametersAreNonnullByDefault
public class WebElementsCollectionWrapper implements WebElementsCollection {
  private final List<WebElement> elements;
  private final Driver driver;
  private final String stepName;

  public WebElementsCollectionWrapper(Driver driver, Collection<? extends WebElement> elements) {
    this.stepName = null;
    this.driver = driver;
    this.elements = new ArrayList<>(elements);
  }

  public WebElementsCollectionWrapper(String stepName, Driver driver, Collection<? extends WebElement> elements) {
    this.stepName = stepName;
    this.driver = driver;
    this.elements = new ArrayList<>(elements);
  }

  @Override
  @CheckReturnValue
  @Nonnull
  public List<WebElement> getElements() {
    return elements;
  }

  @Override
  @CheckReturnValue
  @Nonnull
  public String description() {
    if (this.stepName == null)
      return "$$(" + elements.size() + " elements)";
    else
      return this.stepName + " $$(" + elements.size() + " elements)";
  }

  @Override
  public String getStepName() {
    return this.stepName;
  }

  @Override
  @CheckReturnValue
  @Nonnull
  public Driver driver() {
    return driver;
  }
}
