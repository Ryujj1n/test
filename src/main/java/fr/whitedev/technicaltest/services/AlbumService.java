package fr.whitedev.technicaltest.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.whitedev.technicaltest.models.Album;
import fr.whitedev.technicaltest.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

// TODO: explain @Service, why @Autowired on constructor
// Explanation :
// @Service indicate the following class is a service class of the spring application. It is used to implement the business logic
// @Autowired is used on the constructor, which indicate to the framework spring to inject a AlbumRepository dependency
// when the constructor is called and an instance is created

@Service
public class AlbumService {

	private final AlbumRepository albumRepository;
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
		List<Album> allAlbums = getAlbums();
		if (albumId != null) {
			return allAlbums.stream()
					.filter(album -> album.getId() == albumId)
					.toList();
		} else {
			return allAlbums;
		}
	}

	public List<Album> getAlbumsByUserId(int userId) {
		return getAlbums()
				.stream()
				.filter(album -> album.getUserId() == userId)
				.toList();
	}

	public List<String> getAlbumsTitle() {
		return getAlbums()
				.stream()
				.map(Album::getTitle)
				.toList();
	}

	public List<Integer> getUserIds() {
		return getAlbums()
				.stream()
				.map(Album::getUserId)
				.distinct()
				.toList();
	}
}
