package fr.whitedev.technicaltest.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.whitedev.technicaltest.AbstractTest;
import fr.whitedev.technicaltest.models.Album;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DataServiceTest extends AbstractTest {

	@Mock
	private UserService userServiceMock;

	@Mock
	private AlbumService albumServiceMock;

	@Mock
	private List<Album> albumListMock;

	private DataService dataService;

	@BeforeAll
	public void init() throws JsonProcessingException {
		super.init();
	}

	@BeforeEach
	public void initService() {
		Mockito.reset(this.userServiceMock);
		Mockito.reset(this.albumServiceMock);
		this.dataService = new DataService(this.userServiceMock, this.albumServiceMock);
	}

	@Test
	public void shouldGetAlbumsByUsers() {
		when(this.userServiceMock.getUsers(null)).thenReturn(this.users);
		when(this.albumServiceMock.getAlbumsByUserId(anyInt())).thenReturn(albumListMock);

		assertThat(this.dataService.getAlbumsByUsers()).hasSize(10);
		verify(this.userServiceMock, times(1)).getUsers(null);
		verify(this.albumServiceMock, times(10)).getAlbumsByUserId(anyInt());
	}
}
