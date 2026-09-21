package com.zkauansantos.sensors.device.management.api.config.web;

import com.zkauansantos.sensors.device.management.api.client.SensorMonitoringClientBadGatewayException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.URI;
import java.nio.channels.ClosedChannelException;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({
            SocketException.class,
            ConnectException.class,
            ClosedChannelException.class
    })
    public ProblemDetail handle(IOException exception){
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.GATEWAY_TIMEOUT);

        problem.setTitle("Gateway Timeout");
        problem.setDetail(exception.getMessage());
        problem.setType((URI.create(("/errors/gateway-timeout"))));

        return problem;
    }

    @ExceptionHandler({
            SensorMonitoringClientBadGatewayException.class
    })
    public ProblemDetail handle(SensorMonitoringClientBadGatewayException exception){
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_GATEWAY);

        problem.setTitle("Bad Gateway");
        problem.setDetail(exception.getMessage());
        problem.setType((URI.create(("/errors/bad-gateway"))));

        return problem;
    }
}
