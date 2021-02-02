package fr.whitedev.technicaltest.services;

import fr.whitedev.technicaltest.models.Album;
import fr.whitedev.technicaltest.models.User;
import fr.whitedev.technicaltest.models.UserAlbum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public final class DataService {

	private UserService userService;
	private AlbumService albumService;

	public List<User> getUsers(Integer userId) {
		// TODO: implement method to get ALL users, or ONE user if userId is not null
		return Collections.emptyList();
	}

	public List<Album> getAlbums(Integer albumId) {
		// TODO: implement method to get ALL albums, or ONE album if albumId is not null
		return Collections.emptyList();
	}

	public List<UserAlbum> getAlbumsByUsers() {
		// TODO: implement method to get ALL users with ALL of their albums
		return Collections.emptyList();
	}
}
