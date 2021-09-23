package build.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Scanner;

public class BuildDAOAction {

	static BuildDAO bDAO = BuildDAOFactory.getBuildDAO();

	// 讀取CSV並且寫入DB

	public void storeFileToDB() throws SQLException {

		BuildItem bc = new BuildItem();

		File file = new File("C:\\JDBCfile\\build\\building.csv");
		try (FileInputStream fis = new FileInputStream(file);
				InputStreamReader isr = new InputStreamReader(fis, "UTF8");
				BufferedReader br = new BufferedReader(isr);) {

			String line = null;
			while ((line = br.readLine()) != null) {
				String[] coArray = line.split(",");// 用逗號切割

				bc.setBd_region(coArray[0]);
				bc.setBd_dealdate(coArray[1]);
				bc.setBd_buildtype(coArray[2]);
				bc.setBd_loc(coArray[3]);
				bc.setBd_area(Double.parseDouble(coArray[4]));
				bc.setBd_price(Integer.parseInt(coArray[5]));
				bc.setBd_parking(coArray[6]);
				bc.setBd_remark(coArray[7]);
				bDAO.addBuildItem(bc);

			}

		} catch (FileNotFoundException e) {
			System.out.println("e.getMessage()" + e.getMessage());
		} catch (UnsupportedEncodingException e) {
			System.out.println("e.getMessage()" + e.getMessage());
		} catch (IOException e1) {
			System.out.println("e1.getMessage()" + e1.getMessage());
		}

	}

	// 將查詢到的資料顯示
	public void queryData() throws SQLException {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		while (true) {
			try {
				System.out.println("請輸入四位數字的ID號碼");
				int answer = Integer.valueOf(scanner.nextLine());
				BuildItem bq = bDAO.findByID(answer);
				System.out.println("id:" + bq.getBd_id());
				System.out.println("region:" + bq.getBd_region());
				System.out.println("dealdate:" + bq.getBd_dealdate());
				System.out.println("buildingtype:" + bq.getBd_buildtype());
				System.out.println("loc:" + bq.getBd_loc());
				System.out.println("area:" + bq.getBd_area());
				System.out.println("price:" + bq.getBd_price());
				System.out.println("parking:" + bq.getBd_parking());
				System.out.println("remark:" + bq.getBd_remark());
				System.out.println("請問是否還有要繼續查詢?(1.繼續 2.退出)");

				int anYN = Integer.valueOf(scanner.nextLine());

				if (anYN == 1) {
					continue;
				} else {
					break;
				}

			} catch (SQLException e) {
				System.out.println("錯誤訊息: 錯誤! 請重新輸入 ");
				e.printStackTrace();
			} catch (NullPointerException e) {
				System.out.println("錯誤訊息: 錯誤! 請重新輸入 ");
			} catch (NumberFormatException e) {
				System.out.println("錯誤訊息: 錯誤! 請重新輸入 ");
			}

		}

	}

	// 修改資料：利用ID及區域修改房屋面積
	public void updateArea() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		while (true) {
			try {
				BuildItem update = new BuildItem();

				System.out.println("請輸入要修改資料的區域名稱，例如:安樂區");
				String answer2 = scanner.nextLine();
				update.setBd_region(answer2);

				System.out.println("請輸入要修改資料的四位數字的ID號碼，例如:1100");
				int answer = Integer.valueOf(scanner.nextLine());
				update.setBd_id(answer);

				System.out.println("請輸入更改後的面積資料,例如：100.20");
				Double answer3 = Double.valueOf(scanner.nextLine());
				update.setBd_area(answer3);

				Integer sta = bDAO.updateAreaByIdAndRe(update);

				if (sta >= 1) {
					System.out.println("已更新完成");
				} else {
					System.out.println("更新失敗,資料輸入錯誤");
				}

				System.out.println("請問是否還有要繼續更新資料?(1.繼續 2.退出)");

				int anYN = Integer.valueOf(scanner.nextLine());

				if (anYN == 1) {
					continue;
				} else {
					break;
				}

			} catch (SQLException e) {
				System.out.println("錯誤訊息: 錯誤! 請重新輸入 ");
				e.printStackTrace();
			} catch (NullPointerException e) {
				System.out.println("錯誤訊息: 錯誤! 請重新輸入 ");
			} catch (NumberFormatException e) {
				System.out.println("錯誤訊息: 錯誤! 請重新輸入 ");
			}

		}
	}

	// 主程式
	public static void main(String[] args) throws SQLException {
		BuildDAOAction ac = new BuildDAOAction();

		bDAO.createConn();//開連線
		
		ac.storeFileToDB();// 讀取CSV並且寫入DB

		@SuppressWarnings("resource") // 註解掉警告
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("請輸入要使用的選項：1.查詢總表 2.修改資料 3.查詢單筆資料 4.退出程式");
			int answer = Integer.valueOf(scanner.nextLine());
			if (answer == 1) {
				bDAO.querryALL();// 查詢全部資料表的資料
			} else if (answer == 2) {
				ac.updateArea();// 更新資料
			} else if (answer == 3) {
				ac.queryData();// 將查詢到的資料顯示
			} else if (answer == 4) {
				break;
			} else {
				System.out.println("錯誤!請重新輸入，請輸入對應的阿拉伯數字，例如：1");
			}

			System.out.println("請問是否要繼續執行其他程式，1.是 2.否");
			int answer2 = Integer.valueOf(scanner.nextLine());
			if (answer2 == 1) {
				continue;
			} else {
				break;
			}
		}

		bDAO.deleteMemberById(1005);// 刪除資料

		bDAO.closeConn();
	}
}
