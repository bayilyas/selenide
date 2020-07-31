package com.codeborne.selenide.impl;

import com.codeborne.selenide.Driver;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
public class BySelectorCollection implements WebElementsCollection {

  private final Driver driver;
  private final SearchContext parent;
  private final By selector;
  private final String stepName;

  public BySelectorCollection(Driver driver, By selector) {
    this(driver, null, selector);
  }

  public BySelectorCollection(String stepName, Driver driver, By selector) {
    this(stepName, driver, null, selector);
  }

  public BySelectorCollection(Driver driver, @Nullable SearchContext parent, By selector) {
    this.stepName = null;
    this.driver = driver;
    this.parent = parent;
    this.selector = selector;
  }

  public BySelectorCollection(String stepName, Driver driver, @Nullable SearchContext parent, By selector) {
    this.stepName = stepName;
    this.driver = driver;
    this.parent = parent;
    this.selector = selector;
  }

  @Override
  @CheckReturnValue
  @Nonnull
  public List<WebElement> getElements() {
    SearchContext searchContext = parent == null ? driver.getWebDriver() : parent;
    return WebElementSelector.instance.findElements(driver, searchContext, selector);
  }

  public String getStepName() {
    return stepName;
  }

  @Override
  @CheckReturnValue
  @Nonnull
  public String description() {
    return parent == null ? Describe.selector(selector) :
        (parent instanceof SelenideElement) ?
            ((SelenideElement) parent).getSearchCriteria() + "/" + Describe.shortly(selector) :
            Describe.shortly(selector);
  }

  @Override
  @CheckReturnValue
  @Nonnull
  public Driver driver() {
    return driver;
  }
}
