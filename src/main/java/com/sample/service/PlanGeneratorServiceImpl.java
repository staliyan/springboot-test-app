package com.sample.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sample.datatransferobject.PlanRequestDTO;
import com.sample.datatransferobject.PlanResponseDTO;
import com.sample.exception.ValidationException;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some driver specific things.
 * <p/>
 */
@Service
public class PlanGeneratorServiceImpl implements PlanGeneratorService
{

    private static final Logger LOG = LoggerFactory.getLogger(PlanGeneratorServiceImpl.class);

    private final Integer SCALE = 2;
    private final Integer PRECISION = 4;
    private final BigDecimal DAYS_IN_MONTH = BigDecimal.valueOf(30);
    private final BigDecimal DAYS_IN_YEAR = BigDecimal.valueOf(360);
    private final BigDecimal MONTHS_IN_YEAR = BigDecimal.valueOf(12);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");


    @Override
    public List<PlanResponseDTO> generatePlan(PlanRequestDTO request)
    {

        List<PlanResponseDTO> planSchedule = new ArrayList<>();

        BigDecimal perMonthRate = ratePerMonth(request.getRate());
        BigDecimal initialPrincipal = request.getLoanAmount();
        BigDecimal annuity = calculateAnnuity(perMonthRate, request.getDuration(), initialPrincipal);

        try
        {
            formatter.parse(request.getStartDate());
        }
        catch (DateTimeParseException e)
        {
            throw new ValidationException("Provide Date in dd-MM-yyyy HH:mm:ss format.");
        }
        LocalDateTime startDate = LocalDateTime.parse(request.getStartDate(), formatter);
        LocalDateTime endDate = startDate.plusMonths(request.getDuration().intValue());

        for (LocalDateTime date = startDate; date.isBefore(endDate); date = date.plusMonths(1))
        {
            PlanResponseDTO response = calculateRepayPlanOfMonth(date, annuity, request.getRate(), initialPrincipal);
            initialPrincipal = response.getRemainingOutstandingPrincipal();
            planSchedule.add(response);
        }
        return planSchedule;
    }


    protected PlanResponseDTO calculateRepayPlanOfMonth(LocalDateTime month, BigDecimal annuity, BigDecimal nominalRate, BigDecimal initialPrincipal)
    {

        BigDecimal interest = calculateInterest(nominalRate, initialPrincipal);
        BigDecimal principal = null;
        if (initialPrincipal.longValue() < annuity.longValue())
        {
            principal = initialPrincipal;
        }
        else
        {
            principal = calculatePrincipal(annuity, interest);
        }
        BigDecimal remainingOutstandingPrincipal = calculateRemainingPrincipal(initialPrincipal, principal);

        return new PlanResponseDTO(
            interest, principal, annuity,
            initialPrincipal, remainingOutstandingPrincipal, month.format(formatter));

    }


    protected BigDecimal calculateInterest(BigDecimal nominalRate, BigDecimal principal)
    {

        return principal
            .multiply(DAYS_IN_MONTH)
            .multiply(nominalRate)
            .divide(DAYS_IN_YEAR, SCALE, RoundingMode.FLOOR)
            .divide(BigDecimal.valueOf(100), SCALE, RoundingMode.FLOOR);
    }


    protected BigDecimal ratePerMonth(BigDecimal rate)
    {
        return rate
            .divide(MONTHS_IN_YEAR, PRECISION, RoundingMode.FLOOR)
            .divide(BigDecimal.valueOf(100), PRECISION, RoundingMode.CEILING);
    }


    protected BigDecimal calculateAnnuity(
        BigDecimal perMonthMarkup,
        BigDecimal duration, BigDecimal loanAmount)
    {

        BigDecimal temp = BigDecimal.ONE.add(perMonthMarkup);
        BigDecimal numerator = temp.pow(duration.intValue());
        BigDecimal denominator = numerator.subtract(BigDecimal.ONE);
        return perMonthMarkup
            .multiply(loanAmount)
            .multiply(numerator)
            .divide(denominator, SCALE, RoundingMode.CEILING);
    }


    protected BigDecimal calculatePrincipal(BigDecimal annuity, BigDecimal interest)
    {
        return annuity.subtract(interest);
    }


    protected BigDecimal calculateRemainingPrincipal(
        BigDecimal initialPrincipal,
        BigDecimal monthlyPrincipal)
    {

        return initialPrincipal.subtract(monthlyPrincipal);
    }
}
