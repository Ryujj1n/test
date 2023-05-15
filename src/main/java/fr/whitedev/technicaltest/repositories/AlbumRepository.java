package fr.whitedev.technicaltest.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.whitedev.technicaltest.models.Album;
import org.springframework.stereotype.Repository;

import java.util.List;

// TODO: explain extends, @Repository, throws
// Explanation :
// extends is used to implement all the methods & variables of Abstract repository but have to implement the abstract method of AbstractRepository
// @Repository is used to indicate the following class is a Repository class in a springboot application. It is used for the persistance of the data
// Throws indicate that the following method can throw a JsonProcessingException during his execution

@Repository
public class AlbumRepository extends AbstractRepository {

	public List<Album> getAlbums() throws JsonProcessingException {
		return objectMapper.readValue(
				getBodyByFileName("albums.json"),
				objectMapper.getTypeFactory()
						.constructCollectionType(List.class, Album.class));
	}
}
