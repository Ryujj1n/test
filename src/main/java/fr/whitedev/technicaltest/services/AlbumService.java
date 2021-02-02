package fr.whitedev.technicaltest.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.whitedev.technicaltest.models.Album;
import fr.whitedev.technicaltest.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

// TODO: explain @Service, why @Autowired on constructor

@Service
public class AlbumService {

	private AlbumRepository albumRepository;
	private List<Album> albums;

	@Autowired
	public AlbumService(AlbumRepository albumRepository) {
		this.albumRepository = albumRepository;
	}

	private List<Album> getAlbums() {
		if (this.albums == null) {
			try {
				this.albums = this.albumRepository.getAlbums();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				this.albums = Collections.emptyList();
			}
		}
		return this.albums;
	}

	public List<Album> getAlbums(Integer albumId) {
		// TODO: implement method to get ALL albums, or ONE album if albumId is not null
		return Collections.emptyList();
	}

	public List<Album> getAlbumsByUserId(int userId) {
		// TODO: implement method to get ALL albums of one user
		return Collections.emptyList();
	}

	public List<String> getAlbumsTitle() {
		// TODO: implement method to get ALL titles of the albums
		return Collections.emptyList();
	}

	public List<Integer> getUserIds() {
		// TODO: implement method to get unique ids of users having at least one album
		return Collections.emptyList();
	}
}
