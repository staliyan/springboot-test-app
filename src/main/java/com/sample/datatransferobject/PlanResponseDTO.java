package com.sample.datatransferobject;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlanResponseDTO
{

    private String date;

    private BigDecimal interest;

    private BigDecimal principal;

    private BigDecimal borrowerPaymentAmount;

    private BigDecimal initialOutstandingPrincipal;

    private BigDecimal remainingOutstandingPrincipal;


    private PlanResponseDTO()
    {}


    public PlanResponseDTO(
        BigDecimal interest, BigDecimal principal, BigDecimal borrowerPaymentAmount,
        BigDecimal initialOutstandingPrincipal, BigDecimal remainingOutstandingPrincipal, String date)
    {
        this.interest = interest;
        this.principal = principal;
        this.borrowerPaymentAmount = borrowerPaymentAmount;
        this.initialOutstandingPrincipal = initialOutstandingPrincipal;
        this.remainingOutstandingPrincipal = remainingOutstandingPrincipal;
        this.date = date;
    }


    public String getDate()
    {
        return date;
    }


    public void setDate(String date)
    {
        this.date = date;
    }


    public BigDecimal getInterest()
    {
        return interest;
    }


    public void setInterest(BigDecimal interest)
    {
        this.interest = interest;
    }


    public BigDecimal getPrincipal()
    {
        return principal;
    }


    public void setPrincipal(BigDecimal principal)
    {
        this.principal = principal;
    }


    public BigDecimal getBorrowerPaymentAmount()
    {
        return borrowerPaymentAmount;
    }


    public void setBorrowerPaymentAmount(BigDecimal borrowerPaymentAmount)
    {
        this.borrowerPaymentAmount = borrowerPaymentAmount;
    }


    public BigDecimal getInitialOutstandingPrincipal()
    {
        return initialOutstandingPrincipal;
    }


    public void setInitialOutstandingPrincipal(BigDecimal initialOutstandingPrincipal)
    {
        this.initialOutstandingPrincipal = initialOutstandingPrincipal;
    }


    public BigDecimal getRemainingOutstandingPrincipal()
    {
        return remainingOutstandingPrincipal;
    }


    public void setRemainingOutstandingPrincipal(BigDecimal remainingOutstandingPrincipal)
    {
        this.remainingOutstandingPrincipal = remainingOutstandingPrincipal;
    }

}
