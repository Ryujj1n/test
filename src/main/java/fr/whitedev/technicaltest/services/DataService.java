package fr.whitedev.technicaltest.services;

import fr.whitedev.technicaltest.models.Album;
import fr.whitedev.technicaltest.models.User;
import fr.whitedev.technicaltest.models.UserAlbum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public final class DataService {

	private UserService userService;
	private AlbumService albumService;

	public List<User> getUsers(Integer userId) {
		// TODO: implement method to get ALL users, or ONE user if userId is not null
		return this.userService.getUsers(userId);
	}

	public List<Album> getAlbums(Integer albumId) {
		// TODO: implement method to get ALL albums, or ONE album if albumId is not null
		return this.albumService.getAlbums(albumId);
	}

	public List<UserAlbum> getAlbumsByUsers() {
		// TODO: implement method to get ALL users with ALL of their albums
		List<User> users = userService.getUsers(null);

		return users.stream()
				.map(user -> {
					List<Album> albums = albumService.getAlbumsByUserId(user.getId());
					List<String> albumsTitle = albums.stream()
							.map(Album::getTitle)
							.toList();
					return new UserAlbum(user.getUsername(), albumsTitle);
				})
				.toList();
	}
}
