package project.hrms.dataacess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import project.hrms.entities.concretes.City;

public interface CityDao extends JpaRepository<City, Integer>{

}
