package project.hrms.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.hrms.business.abstracts.PendingEmployerService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.Employer;
import project.hrms.entities.concretes.PendingEmployer;

@CrossOrigin
@RestController
@RequestMapping("/api/pendingemployers")
public class PendingEmployerController {
	private PendingEmployerService pendingEmployerService;
	public PendingEmployerController(PendingEmployerService pendingEmployerService) {
		this.pendingEmployerService = pendingEmployerService;
	}
	@PostMapping("/update")
	public Result update(@RequestBody PendingEmployer pendingEmployer) {
		return pendingEmployerService.update(pendingEmployer);
	}
	@GetMapping("/ispending")
	public DataResult<Boolean> isPending(){
		return pendingEmployerService.isPending();
	}
	@GetMapping("/applyupdaterequest")
	public Result applyUpdateRequest(int pendingemployerid,int isok) {
		return pendingEmployerService.apply(pendingemployerid, isok);
	}
	@GetMapping("/getall")
	public DataResult<List<PendingEmployer>> getAll(){
		return pendingEmployerService.getAll();
	}
}
