package com.zkauansantos.sensors.device.management.domain.repository;

import com.zkauansantos.sensors.device.management.domain.model.Sensor;
import com.zkauansantos.sensors.device.management.domain.model.SensorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, SensorId> {
}
