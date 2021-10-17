package project.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.hrms.business.abstracts.RoleService;
import project.hrms.core.utilities.results.DataResult;
import project.hrms.core.utilities.results.Result;
import project.hrms.entities.concretes.Role;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin

public class RolesController {
	private RoleService roleService;
	@Autowired
	public RolesController(RoleService roleService) {
		super();
		this.roleService = roleService;
	}
	@GetMapping("/getall")
	public DataResult<List<Role>> getAll(){
		return roleService.getAll();
	}
	@PostMapping("/add")
	public Result add(@RequestBody Role role) {
		return roleService.add(role);
	}
	@GetMapping("/delete")
	public Result delete(int roleid) {
		return roleService.delete(roleid);
	}
	@GetMapping("/getrolebyid")
	public DataResult<Role> getRoleById(int roleid){
		return roleService.getRoleByRoleId(roleid);
	}
}
