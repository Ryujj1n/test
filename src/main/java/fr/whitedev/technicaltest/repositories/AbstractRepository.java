package fr.whitedev.technicaltest.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import io.micrometer.core.instrument.util.IOUtils;

import java.io.InputStream;

// TODO: explain abstract, protected, why final method
// Explanation :
// abstract : an abstract class can't be instantiate, it can be used as a "model" for others class. It can contain methods (abstract or not)
// All the class that will extends this abstract class will have to implement the abstract methods
// Protected means the variable can be used by the method and the one which will extend it
// Final mean the method can't be rewrite except with the annotation @Override

public abstract class AbstractRepository {

	protected ObjectMapper objectMapper = new ObjectMapper();

	protected final String getBodyByFileName(String filename) {
		InputStream notFoundStream = AbstractRepository.class.getClassLoader()
															 .getResourceAsStream(filename);
		return IOUtils.toString(notFoundStream, Charsets.UTF_8);
	}
}
