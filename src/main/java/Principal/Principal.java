package Principal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
	public static void main(String[] args) {
		// Pon aquí la ruta correcta de tu CSV (ajústala si está en la carpeta "datos/")
		String rutaCsv = "datos/anuncios_vendedor.csv";

		List<Anuncio> listaAnuncios = new ArrayList<>();
		Set<Vendedor> setVendedores = new LinkedHashSet<>();
		boolean tieneVendedores = false;

		try (BufferedReader br = new BufferedReader(new FileReader(rutaCsv))) {
			String lineaCabecera = br.readLine();
			if (lineaCabecera == null) {
				System.out.println("El fichero CSV está vacío.");
				return;
			}

			// Detección automática si el CSV incluye datos de vendedores
			tieneVendedores = lineaCabecera.toLowerCase().contains("vendedor") || lineaCabecera.toLowerCase().contains("nombre");

			String linea;
			while ((linea = br.readLine()) != null) {
				// IMPORTANTE: Separamos por punto y coma (;) que es como vienen tus datos
				String[] campos = linea.split(";");

				if (campos.length < 9) continue; // Ignorar líneas vacías o mal formadas

				// Mapeo básico del anuncio
				Anuncio anuncio = new Anuncio(
								Integer.parseInt(campos[0].trim()),
								campos[1].trim(),
								campos[2].trim(),
								Double.parseDouble(campos[3].trim().replace(",", ".")),
								campos[4].trim(),
								campos[5].trim(),
								campos[6].trim(),
								Integer.parseInt(campos[7].trim()),
								Boolean.parseBoolean(campos[8].trim())
				);
				listaAnuncios.add(anuncio);

				// Si el CSV contiene datos de vendedor, los extraemos asegurando unicidad
				if (tieneVendedores && campos.length >= 13) {
					Vendedor vendedor = new Vendedor(
									Integer.parseInt(campos[9].trim()),
									campos[10].trim(),
									campos[11].trim(),
									Integer.parseInt(campos[12].trim())
					);
					setVendedores.add(vendedor); // El Set evita duplicados automáticamente
				}
			}

		} catch (IOException e) {
			System.err.println("Error leyendo el fichero CSV: " + e.getMessage());
			return;
		}

		// Solicitar categoría al usuario (Apartado 3)
		Scanner scanner = new Scanner(System.in);
		System.out.println("Introduce la categoría a filtrar (o escribe 'Todos'): ");
		String categoriaFiltro = scanner.nextLine().trim();

		// Filtrar anuncios según la categoría elegida
		List<Anuncio> anunciosFiltrados = listaAnuncios.stream()
						.filter(a -> categoriaFiltro.equalsIgnoreCase("Todos") || a.getCategoria().equalsIgnoreCase(categoriaFiltro))
						.collect(Collectors.toList());

		// Escribir los resultados a JSON usando Jackson
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT); // Formato con sangría bonita

		try {
			// Guardar anuncios.json
			mapper.writeValue(new File("anuncios.json"), anunciosFiltrados);
			System.out.println("Fichero 'anuncios.json' generado correctamente.");

			// Si aplica, guardar vendedores.json
			if (tieneVendedores && !setVendedores.isEmpty()) {
				mapper.writeValue(new File("vendedores.json"), setVendedores);
				System.out.println("Fichero 'vendedores.json' generado correctamente sin duplicados.");
			}

		} catch (IOException e) {
			System.err.println("Error al escribir los ficheros JSON: " + e.getMessage());
		}
	}
}