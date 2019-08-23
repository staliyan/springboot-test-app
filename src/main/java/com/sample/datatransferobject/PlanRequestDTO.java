package com.sample.datatransferobject;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlanRequestDTO
{
    @NotNull(message = "Duration can not be null!")
    private BigDecimal duration;

    @NotNull(message = "Loan Amount can not be null!")
    private BigDecimal loanAmount;

    @NotNull(message = "Rate of Interest can not be null!")
    private BigDecimal rate;

    @NotNull(message = "Start date can not be null!")
    private String startDate;


    private PlanRequestDTO()
    {}


    private PlanRequestDTO(BigDecimal duration, BigDecimal loanAmount, BigDecimal rate, String startDate)
    {
        this.duration = duration;
        this.loanAmount = loanAmount;
        this.rate = rate;
        this.startDate = startDate;
    }


    public BigDecimal getDuration()
    {
        return duration;
    }


    public BigDecimal getLoanAmount()
    {
        return loanAmount;
    }


    public BigDecimal getRate()
    {
        return rate;
    }


    public String getStartDate()
    {
        return startDate;
    }


    public void setDuration(BigDecimal duration)
    {
        this.duration = duration;
    }


    public void setLoanAmount(BigDecimal loanAmount)
    {
        this.loanAmount = loanAmount;
    }


    public void setRate(BigDecimal rate)
    {
        this.rate = rate;
    }


    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }

}
