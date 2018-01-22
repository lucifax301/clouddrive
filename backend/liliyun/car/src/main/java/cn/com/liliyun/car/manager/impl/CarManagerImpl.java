package cn.com.liliyun.car.manager.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.liliyun.car.manager.ICarManager;
import cn.com.liliyun.car.mapper.CarAccidentMapper;
import cn.com.liliyun.car.mapper.CarAnnualMapper;
import cn.com.liliyun.car.mapper.CarDetectMapper;
import cn.com.liliyun.car.mapper.CarInsuranceMapper;
import cn.com.liliyun.car.mapper.CarLogMapper;
import cn.com.liliyun.car.mapper.CarMapper;
import cn.com.liliyun.car.mapper.CarOilwearMapper;
import cn.com.liliyun.car.mapper.CarRepairMapper;
import cn.com.liliyun.car.mapper.CarTaxMapper;
import cn.com.liliyun.car.model.Car;
import cn.com.liliyun.car.model.CarAccident;
import cn.com.liliyun.car.model.CarAnnual;
import cn.com.liliyun.car.model.CarDetect;
import cn.com.liliyun.car.model.CarInsurance;
import cn.com.liliyun.car.model.CarLog;
import cn.com.liliyun.car.model.CarOilwear;
import cn.com.liliyun.car.model.CarRepair;
import cn.com.liliyun.car.model.CarTax;
import cn.com.liliyun.car.model.CoachCar;
import cn.com.liliyun.common.util.CarChangeType;
import cn.com.liliyun.common.util.CarStatus;
import cn.com.liliyun.importexcel.model.CarOilwearImport;
import cn.com.liliyun.user.model.User;

@Service
public class CarManagerImpl implements ICarManager {
	
	@Autowired
	private CarMapper carMapper;
	
	@Autowired
	private CarAnnualMapper carAnnualMapper;
	
	@Autowired
	private CarDetectMapper carDetectMapper;
	
	@Autowired
	private CarInsuranceMapper carInsuranceMapper;
	
	@Autowired
	private CarOilwearMapper carOilwearMapper;
	
	@Autowired
	private CarRepairMapper carRepairMapper;

	@Autowired
	private CarTaxMapper carTaxMapper;
	
	@Autowired
	private CarAccidentMapper carAccidentMapper;
	
	@Autowired
	private CarLogMapper carLogMapper;
	
	@Override
	public List<Car> queryCarList(Car car) {
		
		return carMapper.queryCarList(car);
	}

	@Override
	public Car queryCar(Car car) {
		return carMapper.selectByPrimaryKey(car);
	}

	@Override
	public void addCar(User user,Car car) {
		carMapper.insertSelective(car);
		
		//新增记录
		CarLog carLog = new CarLog();
		carLog.setDblink(car.getDblink());
		carLog.setCarid(car.getCarId());
		carLog.setCarno(car.getCarNo());
		carLog.setCuid(user.getId());
		carLog.setAreaid(car.getAreaId());
		carLog.setCname(user.getRealname());
		carLog.setCtime(new Date());
		carLog.setName(car.getCoachName());
		carLog.setStatus(car.getStatus());
		carLog.setType(CarChangeType.NEW.getStatus());
		carLogMapper.insertSelective(carLog);
		
		
		if (StringUtils.isNotBlank(car.getCoachName())) {
			carLog.setType(CarChangeType.USE.getStatus());
			carLogMapper.insertSelective(carLog);
			CoachCar coachCar=new CoachCar();
			coachCar.setDblink(car.getDblink());
			coachCar.setCoachid(car.getCoachId());
			coachCar.setCarno(car.getCarNo());
			carMapper.updateCoachCar(coachCar);
		}
		
	}

	@Override
	public void updateCar(User user,Car car) {
		Car before = carMapper.selectByCarNo(car);
		CarLog carLog = new CarLog();
		carLog.setDblink(car.getDblink());
		carLog.setCarid(car.getCarId());
		carLog.setCarno(car.getCarNo());
		carLog.setCuid(user.getId());
		carLog.setAreaid(car.getAreaId());
		carLog.setCname(user.getRealname());
		carLog.setCtime(new Date());
		carLog.setPrename(before.getCoachName());
		carLog.setName(car.getCoachName());
		carLog.setStatus(car.getStatus());
		
		if (car.getStatus() == CarStatus.DONATE.getStatus()) {
			carLog.setType(CarChangeType.DONATE.getStatus());
		} else if(car.getStatus() == CarStatus.STOLEN.getStatus()) {
			carLog.setType(CarChangeType.STOLEN.getStatus());
		} else if (car.getStatus() == CarStatus.RETIRED.getStatus()) {
			carLog.setType(CarChangeType.RETIRED.getStatus());
		} else if (car.getStatus() == CarStatus.STAY_STOP.getStatus()) {
			carLog.setType(CarChangeType.TRANSFER.getStatus());
		} 
		if (before.getStatus() == CarStatus.STAY_STOP.getStatus() 
				&& car.getStatus() == CarStatus.IN_USE.getStatus()) {
			carLog.setType(CarChangeType.USE.getStatus());
		}
		if (before.getStatus() == CarStatus.WAIT_SALE.getStatus() 
				&& car.getStatus() == CarStatus.SALED_OUT.getStatus()) {
			carLog.setType(CarChangeType.SALED_OUT.getStatus());
		}
		if (before.getCoachId() != car.getCoachId()) {
			carLog.setType(CarChangeType.TRANSFER.getStatus());
		}
		
		if (carLog.getType() == null) {
			if(before.getAreaId()==null&&car.getAreaId()!=null){
				carLog.setType(CarChangeType.AREA_TRANSFER.getStatus());
			}else if(before.getAreaId()!=null&&car.getAreaId()==null){
				carLog.setType(CarChangeType.AREA_TRANSFER.getStatus());
			}else if(before.getAreaId().intValue()!=car.getAreaId().intValue()){
				carLog.setType(CarChangeType.AREA_TRANSFER.getStatus());
			}else if(before.getCoachId()==null&&car.getCoachId()!=null){
				carLog.setType(CarChangeType.TRANSFER.getStatus());
			}else if(before.getCoachId()!=null&&car.getCoachId()==null){
				carLog.setType(CarChangeType.TRANSFER.getStatus());
			}else if(before.getCoachId()==null&&car.getCoachId()==null){
				carLog.setType(CarChangeType.TRANSFER.getStatus());
			}else if(before.getCoachId().intValue()!=car.getCoachId().intValue()){
				carLog.setType(CarChangeType.TRANSFER.getStatus());
			}else{
				carLog.setType(CarChangeType.OTHER.getStatus());
			}
			
		}
		carLogMapper.insertSelective(carLog);
		carMapper.updateByPrimaryKeySelective(car);
	}

	@Override
	public void deleteCar(Car car) {
		carMapper.deleteCarList(car);
		//删除时，同步删除所有关联信息
	}
	
	@Override
	public List<CarAnnual> queryCarAnnualList(CarAnnual carAnnual) {
		
		return carAnnualMapper.queryCarAnnualList(carAnnual);
	}

	@Override
	public CarAnnual queryCarAnnual(CarAnnual carAnnual) {
		
		return carAnnualMapper.selectByPrimaryKey(carAnnual.getId());
	}

	@Override
	public void addCarAnnual(CarAnnual carAnnual) {
		carAnnualMapper.insertSelective(carAnnual);
	}

	@Override
	public void updateCarAnnual(CarAnnual carAnnual) {
		carAnnualMapper.updateByPrimaryKeySelective(carAnnual);
	}

	@Override
	public void deleteCarAnnual(CarAnnual carAnnual) {
		carAnnual.setIsDel(1);
		carAnnualMapper.updateCarAnnualList(carAnnual);
	}

	@Override
	public List<CarDetect> queryCarDetectList(CarDetect carDetect) {
		
		return carDetectMapper.queryCarDetectList(carDetect);
	}

	@Override
	public CarDetect queryCarDetect(CarDetect carDetect) {
		
		return carDetectMapper.selectByPrimaryKey(carDetect.getId());
	}

	@Override
	public void addCarDetect(CarDetect carDetect) {
		carDetectMapper.insertSelective(carDetect);

	}

	@Override
	public void updateCarDetect(CarDetect carDetect) {
		carDetectMapper.updateByPrimaryKeySelective(carDetect);

	}

	@Override
	public void deleteCarDetect(CarDetect carDetect) {
		carDetect.setIsDel(1);
		carDetectMapper.updateCarDetectList(carDetect);

	}

	@Override
	public List<CarInsurance> queryCarInsuranceList(CarInsurance carInsurance) {
		
		return carInsuranceMapper.queryCarInsuranceList(carInsurance);
	}

	@Override
	public CarInsurance queryCarInsurance(CarInsurance carInsurance) {
		
		return carInsuranceMapper.selectByPrimaryKey(carInsurance.getId());
	}

	@Override
	public void addCarInsurance(CarInsurance carInsurance) {
		carInsuranceMapper.insertSelective(carInsurance);

	}

	@Override
	public void updateCarInsurance(CarInsurance carInsurance) {
		carInsuranceMapper.updateByPrimaryKeySelective(carInsurance);

	}

	@Override
	public void deleteCarInsurance(CarInsurance carInsurance) {
		carInsurance.setIsDel(1);
		carInsuranceMapper.updateCarInsuranceList(carInsurance);
	}

	@Override
	public List<CarOilwear> queryCarOilwearList(CarOilwear carOilwear) {
		
		return carOilwearMapper.queryCarOilwearList(carOilwear);
	}

	@Override
	public CarOilwear queryCarOilwear(CarOilwear carOilwear) {
		
		return carOilwearMapper.selectByPrimaryKey(carOilwear.getId());
	}

	@Override
	public void addCarOilwear(CarOilwear carOilwear) {
		carOilwearMapper.insertSelective(carOilwear);

	}

	@Override
	public void updateCarOilwear(CarOilwear carOilwear) {
		carOilwearMapper.updateByPrimaryKeySelective(carOilwear);
	}

	@Override
	public void deleteCarOilwear(CarOilwear carOilwear) {
		carOilwear.setIsDel(1);
		carOilwearMapper.updateCarOilwearList(carOilwear);
	}

	@Override
	public List<CarRepair> queryCarRepairList(CarRepair carRepair) {
		
		return carRepairMapper.queryCarRepairList(carRepair);
	}

	@Override
	public CarRepair queryCarRepair(CarRepair carRepair) {
		
		return carRepairMapper.selectByPrimaryKey(carRepair.getId());
	}

	@Override
	public void addCarRepair(CarRepair carRepair) {
		carRepairMapper.insertSelective(carRepair);

	}

	@Override
	public void updateCarRepair(CarRepair carRepair) {
		carRepairMapper.updateByPrimaryKeySelective(carRepair);
	}

	@Override
	public void deleteCarRepair(CarRepair carRepair) {
		carRepair.setIsDel(1);
		carRepairMapper.updateCarRepairList(carRepair);
	}

	@Override
	public List<CarTax> queryCarTaxList(CarTax carTax) {
		
		return carTaxMapper.queryCarTaxList(carTax);
	}

	@Override
	public CarTax queryCarTax(CarTax carTax) {
		
		return carTaxMapper.selectByPrimaryKey(carTax.getId());
	}

	@Override
	public void addCarTax(CarTax carTax) {
		carTaxMapper.insertSelective(carTax);
	}

	@Override
	public void updateCarTax(CarTax carTax) {
		carTaxMapper.updateByPrimaryKeySelective(carTax);
	}

	@Override
	public void deleteCarTax(CarTax carTax) {
		carTax.setIsDel(1);
		carTaxMapper.updateCarTaxList(carTax);
	}

	@Override
	public List<CarAccident> queryCarAccidentList(CarAccident carAccident) {
		return carAccidentMapper.selectList(carAccident);
	}

	@Override
	public CarAccident queryCarAccident(CarAccident carAccident) {
		return carAccidentMapper.selectByPrimaryKey(carAccident);
	}

	@Override
	public void addCarAccident(CarAccident carAccident) {
		carAccidentMapper.insertSelective(carAccident);
	}

	@Override
	public void updateCarAccident(CarAccident carAccident) {
		carAccidentMapper.updateByPrimaryKeySelective(carAccident);
	}

	@Override
	public Car queryCarByCarno(Car car) {
		return carMapper.selectByCarNo(car);
	}

	@Override
	public void importCarOilwear(Map map) {
		carOilwearMapper.addCarOilwearBatch(map);
	}

	@Override
	public List<Car> queryCarListOilCards(Map map) {
		return carMapper.queryByOilCards(map);
	}

}
