package fr.whitedev.technicaltest.controllers;

import fr.whitedev.technicaltest.models.Album;
import fr.whitedev.technicaltest.models.User;
import fr.whitedev.technicaltest.models.UserAlbum;
import fr.whitedev.technicaltest.services.DataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// TODO: explain @RestController, @GetMapping, why @Autowired is missing, why final class, why private attribute
// Explanation :
// @RestController : Annotation which is used to indicate that the following class is a spring controller which expose endpoint for Rest API
// @GetMapping : Annotation that indicate the following method is used to interact with the HTTP Get Request with the specify path { ex : /albums }
// @Autowired is missing means the dependence of DataService is not injected but with the annotation @AllArgsConstructor, it will be generated in the constructor of the controller
// Private attribute is used on DataService to encapsulate the variable inside the class and does not permit a call of the variable outside the class

@RestController()
@RequestMapping("/api/v0")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public final class DataController {

	private DataService dataService;

	@GetMapping(value = { "/albums", "/albums/{id}" })
	public List<Album> getAlbums(@PathVariable(required = false) Integer id) {
		return this.dataService.getAlbums(id);
	}

	@GetMapping(value = { "/users", "/users/{id}" })
	public List<User> getUsers(@PathVariable(required = false) Integer id) {
		return this.dataService.getUsers(id);
	}

	@GetMapping("/users/albums")
	public List<UserAlbum> getAlbumsByUsers() {
		return this.dataService.getAlbumsByUsers();
	}
}
