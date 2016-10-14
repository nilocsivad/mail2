import java.lang.reflect.Field;

public class ClassInvoke {

	public ClassInvoke() {
	}

	public static void main(String[] args) {


		String line = "\r\n-----------------------------------------";

		Object obj = new ClassInvoke();
		Class<?> cls = obj.getClass();
		Field[] fields = cls.getDeclaredFields();

		System.out.println(line);
		for (Field field : fields) {
			String f = field.getName();
			System.out.print(f + ", ");
		}

		System.out.println(line);
		for (Field field : fields) {
			String f = field.getName();
			System.out.print("#{" + f + "}, ");
		}

		System.out.println(line);
		for (Field field : fields) {
			String f = field.getName();
			System.out.print("#" + f + "#, ");
		}

		System.out.println(line);
		for (Field field : fields) {
			String f = field.getName();
			System.out.print("#{item." + f + "}, ");
		}

		System.out.println(line);
		for (Field field : fields) {
			String f = field.getName();
			System.out.print("#list[]." + f + "#, ");
		}

		System.out.println(line);
		for (Field field : fields) {
			String f = field.getName();
			System.out.print("#array[]." + f + "#, ");
		}

		System.out.println(line);
		for (Field field : fields) {
			String f = field.getName();
			System.out.println("\t\t\t<if test=\"" + f + " != null and " + f + " != ''\"> AND " + f + " = #{" + f + "} </if>");
		}

		System.out.println(line);
		for (Field field : fields) {
			String f = field.getName();
			System.out.println("\t\t\t<if test=\"" + f + " != null and " + f + " != ''\"> , " + f + " = #{" + f + "} </if>");
		}

		System.out.println(line);
		String format = "<isNotEmpty prepend=\",\" property=\"%s\"> %s = #%s# </isNotEmpty>";
		for (Field field : fields) {
			String f = field.getName();
			System.out.println(String.format("\t\t\t" + format, f, f, f));
		}

		System.out.println(line);
		format = "<isNotEmpty prepend=\"AND\" property=\"obj.%s\"> %s = #obj.%s# </isNotEmpty>";
		for (Field field : fields) {
			String f = field.getName();
			System.out.println(String.format("\t\t\t" + format, f, f, f));
		}

	}

}
