/*
NAME:-ASHUTOSH DUBEY
E-MAIL: dashutosh404@gmail.com
 */
import org.web3j.protocol.Web3j;//importing web3j's libraries
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;//importing web3j's HttpService
import org.web3j.protocol.core.methods.response.EthBlockNumber;//EthBlockNumber method of Web3j
import java.lang.String;// to convert the block hash into a number
import java.lang.*;// to convert the block hash into a number
import java.math.BigDecimal;//math functions
import java.math.BigInteger;//math function
import java.math.RoundingMode;//math function to round of the balance
import java.util.concurrent.ExecutionException;//catch Execution error
import java.util.concurrent.TimeUnit;//convert and perform time delay

public class Synergy { //defining the class
    public static void main(String[] args) throws Exception, ExecutionException, InterruptedException {   //catching execution and interruption exception
        Web3j web3 = Web3j.build(new HttpService("https://mainnet.infura.io/v3/da890cb5998c43c2889cb855d2d8a05d")); //Supplying our Ethereum node to the web3 variable
        EthBlockNumber result = web3.ethBlockNumber().sendAsync().get();//Using ethBlockNumber method to send an async get request to the node
        System.out.println(" The Block Number is: " + result.getBlockNumber().toString());//printing our block number after getting the number using getBlockNumber method and then converting it to a string using toString method
        String ethAddress = "0x07495Ea80CB0ED5a9A07F1E67ddD19c9121A3112";// ethereum address for which we have to fetch the balance
        EthGetBalance balanceResponse = web3
                .ethGetBalance(ethAddress, DefaultBlockParameter.valueOf("latest")).sendAsync( )//using ethGetBalance method to send an async get request to the node
                .get(10, TimeUnit.SECONDS);//get ideally using timeout, return in time units seconds
        BigInteger unscaledBalance = balanceResponse.getBalance();//as balance is big integer & is unscalled balance
        BigDecimal scaledBalance = new BigDecimal (unscaledBalance)// Big decimal for scaled balance
                .divide (new BigDecimal ( 1000000000000000000L), 18, RoundingMode.HALF_UP);//creating a new big decimal and with 18 zeroes and Set it to scale 18
        System.out.println("Unscaled balance is : " + unscaledBalance);//printing the unscaled balance
        System.out.println("Scaled balance is : " + scaledBalance);//printing the scaled balance
    }
}